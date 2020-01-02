package de.harakal.flaggie.bitmap

import android.graphics.Bitmap
import kotlin.math.abs

/**
 * Image processing manipulation utils
 */
object BitmapUtils {
    /** Crop Bitmap to maintain aspect ratio of model input.   */
    fun cropBitmap(bitmap: Bitmap, modelHeight: Float, modelWidth: Float): Bitmap {
        val bitmapRatio = bitmap.height.toFloat() / bitmap.width
        val modelInputRatio = modelHeight / modelWidth
        var croppedBitmap = bitmap

        // Acceptable difference between the modelInputRatio and bitmapRatio to skip cropping.
        val maxDifference = 1e-5

        // Checks if the bitmap has similar aspect ratio as the required model input.
        when {
            abs(modelInputRatio - bitmapRatio) < maxDifference -> return croppedBitmap
            modelInputRatio < bitmapRatio -> {
                // New image is taller so we are height constrained.
                val cropHeight = bitmap.height - (bitmap.width.toFloat() / modelInputRatio)
                croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    0,
                    (cropHeight / 2).toInt(),
                    bitmap.width,
                    (bitmap.height - cropHeight).toInt()
                )
            }
            else -> {
                val cropWidth = bitmap.width - (bitmap.height.toFloat() * modelInputRatio)
                croppedBitmap = Bitmap.createBitmap(
                    bitmap,
                    (cropWidth / 2).toInt(),
                    0,
                    (bitmap.width - cropWidth).toInt(),
                    bitmap.height
                )
            }
        }
        return croppedBitmap
    }

    // This value is 2 ^ 18 - 1, and is used to hold the RGB values together before their ranges
    // are normalized to eight bits.
    private const val MAX_CHANNEL_VALUE = 262143

    /** Helper function to convert y,u,v integer values to RGB format */
    private fun convertYUVToRGB(y: Int, u: Int, v: Int): Int {
        // Adjust and check YUV values
        val yNew = if (y - 16 < 0) 0 else y - 16
        val uNew = u - 128
        val vNew = v - 128
        val expandY = 1192 * yNew
        var r = expandY + 1634 * vNew
        var g = expandY - 833 * vNew - 400 * uNew
        var b = expandY + 2066 * uNew

        // Clipping RGB values to be inside boundaries [ 0 , MAX_CHANNEL_VALUE ]
        val checkBoundaries = { x: Int ->
            when {
                x > MAX_CHANNEL_VALUE -> MAX_CHANNEL_VALUE
                x < 0 -> 0
                else -> x
            }
        }
        r = checkBoundaries(r)
        g = checkBoundaries(g)
        b = checkBoundaries(b)
        return -0x1000000 or (r shl 6 and 0xff0000) or (g shr 2 and 0xff00) or (b shr 10 and 0xff)
    }

    /** Converts YUV420 format image data (ByteArray) into ARGB8888 format with IntArray as output. */
    fun convertYUV420ToARGB8888(
        yData: ByteArray,
        uData: ByteArray,
        vData: ByteArray,
        width: Int,
        height: Int,
        yRowStride: Int,
        uvRowStride: Int,
        uvPixelStride: Int,
        out: IntArray
    ) {
        var outputIndex = 0
        for (j in 0 until height) {
            val positionY = yRowStride * j
            val positionUV = uvRowStride * (j shr 1)

            for (i in 0 until width) {
                val uvOffset = positionUV + (i shr 1) * uvPixelStride

                // "0xff and" is used to cut off bits from following value that are higher than
                // the low 8 bits
                out[outputIndex] = convertYUVToRGB(
                    0xff and yData[positionY + i].toInt(), 0xff and uData[uvOffset].toInt(),
                    0xff and vData[uvOffset].toInt()
                )
                outputIndex += 1
            }
        }
    }
}