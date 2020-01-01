package de.harakal.flaggie.android.live

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import de.harakal.flaggie.android.R
import de.harakal.flaggie.bitmap.AssetBitmapProvider
import de.harakal.flaggie.bitmap.BitmapUtils
import de.harakal.flaggie.camera.LiveCameraPreview
import de.harakal.flaggie.cartoon.PoseToHandAngles
import de.harakal.flaggie.ml.TensorflowLitePoseEstimator
import de.harakal.flaggie.ui.stickman.PencilCase
import de.harakal.flaggie.ui.stickman.Stickman

const val MODEL_WIDTH = 257
const val MODEL_HEIGHT = 257

/**
 * Activity shows cartoon like live view from Camera
 */
class LiveViewActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private val pencilCase = PencilCase()
    private lateinit var posenet: TensorflowLitePoseEstimator


    private var surfaceHolder: SurfaceHolder? = null
    private var surfaceView: SurfaceView? = null
    private lateinit var liveCameraPreview: LiveCameraPreview

    private val stickman = Stickman()
    private val handAngles = PoseToHandAngles()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_view)
        surfaceView = findViewById(R.id.surfaceView)
        surfaceView!!.holder.addCallback(this)
        posenet = TensorflowLitePoseEstimator(this.applicationContext)
        liveCameraPreview = LiveCameraPreview(this)
    }

    override fun onResume() {
        super.onResume()
        liveCameraPreview.startBackgroundThread()
    }

    override fun onStart() {
        super.onStart()
        liveCameraPreview.openCamera()
    }

    override fun onPause() {
        liveCameraPreview.closeCamera()
        liveCameraPreview.stopBackgroundThread()
        super.onPause()
    }

    private fun loadImageFromAsset() {
        //val bmp = AssetBitmapProvider.getBitmapFromAsset(this, "A_a.jpg")
        val bmp = AssetBitmapProvider.getBitmapFromAsset(this, "A_e.jpg")
        processImage(bmp!!)
    }

    /** Process image using Posenet library.   */
    private fun processImage(bitmap: Bitmap) {
        // Crop bitmap.
        val croppedBitmap =
            BitmapUtils.cropBitmap(bitmap, MODEL_HEIGHT.toFloat(), MODEL_WIDTH.toFloat())

        // Created scaled version of bitmap for model input.
        val scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, MODEL_WIDTH, MODEL_HEIGHT, true)

        val person = posenet.estimateSinglePose(scaledBitmap)
        Log.d("XXX", person.toString())
        surfaceHolder?.let {
            val canvas: Canvas = it.lockCanvas()
            try {
                stickman.drawStickman(
                    canvas,
                    pencilCase,
                    handAngles.poseToHands(person)
                )
            } finally {
                surfaceHolder!!.unlockCanvasAndPost(canvas)
            }
        }
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        this.surfaceHolder = holder
        loadImageFromAsset()
    }
}
