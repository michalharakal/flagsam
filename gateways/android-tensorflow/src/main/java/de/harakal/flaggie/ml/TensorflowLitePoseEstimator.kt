package de.harakal.flaggie.ml

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.gpu.GpuDelegate
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import kotlin.math.exp

/**
 * Implementation of [PoseEstimator] using Tensorflow lite library. Model is stored localy and ist loaded during
 * initialization
 */
class TensorflowLitePoseEstimator(context: Context) : PoseEstimator, AutoCloseable {

    private val interpreter: Interpreter

    init {
        val options = Interpreter.Options()
        interpreter = Interpreter(loadModelFile(context), options)
    }


    /** Preload and memory map the model file, returning a MappedByteBuffer containing the model. */
    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("posenet_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        return inputStream.channel.map(
            FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength
        )
    }

    private var gpuDelegate: GpuDelegate? = null

    fun estimateSinglePose(bitmap: Bitmap): Person {
        return estimateSinglePose(
            bitmap.height,
            bitmap.width,
            BitmapConverter.initInputArrayFromBitmap(bitmap)
        )
    }


    override fun estimateSinglePose(width: Int, height: Int, bitmapBuffer: ByteBuffer): Person {

        val inputArray = arrayOf(bitmapBuffer)
        val outputMap = initOutputMap(interpreter)
        interpreter.runForMultipleInputsOutputs(inputArray, outputMap)
        return calculateHeats(width, height, outputMap)
    }

    override fun close() {
        interpreter.close()
        gpuDelegate?.close()
        gpuDelegate = null
    }

    /** Returns value within [0,1].   */
    private fun sigmoid(x: Float): Float {
        return (1.0f / (1.0f + exp(-x)))
    }

    @Suppress("UNCHECKED_CAST")
    private fun calculateHeats(
        imageWidth: Int,
        imageHeight: Int,
        outputMap: java.util.HashMap<Int, Any>
    ): Person {
        val heatmaps = outputMap[0] as Array<Array<Array<FloatArray>>>
        val offsets = outputMap[1] as Array<Array<Array<FloatArray>>>

        val height = heatmaps[0].size
        val width = heatmaps[0][0].size
        val numKeypoints = heatmaps[0][0][0].size

        // Finds the (row, col) locations of where the keypoints are most likely to be.
        val keypointPositions = Array(numKeypoints) { Pair(0, 0) }
        for (keypoint in 0 until numKeypoints) {
            var maxVal = heatmaps[0][0][0][keypoint]
            var maxRow = 0
            var maxCol = 0
            for (row in 0 until height) {
                for (col in 0 until width) {
                    heatmaps[0][row][col][keypoint] = heatmaps[0][row][col][keypoint]
                    if (heatmaps[0][row][col][keypoint] > maxVal) {
                        maxVal = heatmaps[0][row][col][keypoint]
                        maxRow = row
                        maxCol = col
                    }
                }
            }
            keypointPositions[keypoint] = Pair(maxRow, maxCol)
        }

        // Calculating the x and y coordinates of the keypoints with offset adjustment.
        val xCoords = IntArray(numKeypoints)
        val yCoords = IntArray(numKeypoints)
        val confidenceScores = FloatArray(numKeypoints)
        keypointPositions.forEachIndexed { idx, position ->
            val positionY = keypointPositions[idx].first
            val positionX = keypointPositions[idx].second
            yCoords[idx] = (
                    position.first / (height - 1).toFloat() * imageHeight +
                            offsets[0][positionY][positionX][idx]
                    ).toInt()
            xCoords[idx] = (
                    position.second / (width - 1).toFloat() * imageWidth +
                            offsets[0][positionY]
                                    [positionX][idx + numKeypoints]
                    ).toInt()
            confidenceScores[idx] = sigmoid(heatmaps[0][positionY][positionX][idx])
        }

        val keypointList = Array(numKeypoints) { KeyPoint() }
        var totalScore = 0.0f
        enumValues<BodyPart>().forEachIndexed { idx, it ->
            keypointList[idx] =
                KeyPoint(it, Position(xCoords[idx], yCoords[idx]), confidenceScores[idx])
            totalScore += confidenceScores[idx]
        }

        return Person(keyPoints = keypointList.toList(), score = totalScore / numKeypoints)
    }

    /**
     * Initializes an outputMap of 1 * x * y * z FloatArrays for the model processing to populate.
     */
    @SuppressLint("UseSparseArrays")
    private fun initOutputMap(interpreter: Interpreter): HashMap<Int, Any> {
        val outputMap = HashMap<Int, Any>()

        // 1 * 9 * 9 * 17 contains heatmaps
        val heatmapsShape = interpreter.getOutputTensor(0).shape()
        outputMap[0] = Array(heatmapsShape[0]) {
            Array(heatmapsShape[1]) {
                Array(heatmapsShape[2]) { FloatArray(heatmapsShape[3]) }
            }
        }

        // 1 * 9 * 9 * 34 contains offsets
        val offsetsShape = interpreter.getOutputTensor(1).shape()
        outputMap[1] = Array(offsetsShape[0]) {
            Array(offsetsShape[1]) { Array(offsetsShape[2]) { FloatArray(offsetsShape[3]) } }
        }

        // 1 * 9 * 9 * 32 contains forward displacements
        val displacementsFwdShape = interpreter.getOutputTensor(2).shape()
        outputMap[2] = Array(offsetsShape[0]) {
            Array(displacementsFwdShape[1]) {
                Array(displacementsFwdShape[2]) { FloatArray(displacementsFwdShape[3]) }
            }
        }

        // 1 * 9 * 9 * 32 contains backward displacements
        val displacementsBwdShape = interpreter.getOutputTensor(3).shape()
        outputMap[3] = Array(displacementsBwdShape[0]) {
            Array(displacementsBwdShape[1]) {
                Array(displacementsBwdShape[2]) { FloatArray(displacementsBwdShape[3]) }
            }
        }

        return outputMap
    }
}