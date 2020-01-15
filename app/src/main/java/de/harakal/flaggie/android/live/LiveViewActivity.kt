package de.harakal.flaggie.android.live

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import de.harakal.flaggie.android.R
import de.harakal.flaggie.bitmap.AssetBitmapProvider
import de.harakal.flaggie.bitmap.BitmapUtils
import de.harakal.flaggie.camera.LiveCameraPreview
import de.harakal.flaggie.cartoon.PoseToHandAngles
import de.harakal.flaggie.ml.TensorflowLitePoseEstimator
import de.harakal.flaggie.pipeline.Pose2Char
import de.harakal.flaggie.ui.stickman.PencilCase
import de.harakal.flaggie.ui.stickman.Stickman

const val MODEL_WIDTH = 257
const val MODEL_HEIGHT = 257

private const val REQUEST_CODE_PERMISSIONS = 10

/**
 * Activity shows cartoon like live view from Camera
 */
class LiveViewActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private val pencilCase = PencilCase()
    private lateinit var posenet: TensorflowLitePoseEstimator

    // This is an array of all the permission specified in the manifest.
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)


    private var surfaceHolder: SurfaceHolder? = null
    private lateinit var surfaceView: SurfaceView
    private lateinit var liveCameraPreview: LiveCameraPreview

    private val stickman = Stickman()
    private val handAngles = PoseToHandAngles()
    private val pose2Char = Pose2Char()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_view)
        surfaceView = findViewById(R.id.surfaceView)
        surfaceView.holder.addCallback(this)
        posenet = TensorflowLitePoseEstimator(this.applicationContext)
        liveCameraPreview = LiveCameraPreview(this) { bitmap -> processImage(bitmap) }

        if (allPermissionsGranted()) {
            //surfaceView.post { liveCameraPreview.openCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onResume() {
        super.onResume()
        //liveCameraPreview.startBackgroundThread()
    }

    override fun onPause() {
        //liveCameraPreview.closeCamera()
        // liveCameraPreview.stopBackgroundThread()
        super.onPause()
    }

    private fun loadImageFromAsset() {
        val bmp = AssetBitmapProvider.getBitmapFromAsset(this, "A_c.jpg")
        processImage(bmp!!)
    }

    /**
     * Process result from permission request dialog box, has the request
     * been granted? If yes, start Camera. Otherwise display a toast
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                surfaceView!!.post {
                   // liveCameraPreview.openCamera()
                }
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    /**
     * Check if all permission specified in the manifest have been granted
     */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    /** Process image using Posenet library.   */
    private fun processImage(bitmap: Bitmap) {
        // Crop bitmap.
        val croppedBitmap =
            BitmapUtils.cropBitmap(bitmap, MODEL_HEIGHT.toFloat(), MODEL_WIDTH.toFloat())

        // Created scaled version of bitmap for model input.
        val scaledBitmap = Bitmap.createScaledBitmap(croppedBitmap, MODEL_WIDTH, MODEL_HEIGHT, true)

        val person = posenet.estimateSinglePose(scaledBitmap)
        val hands = handAngles.poseToHands(person)

        Log.d("XXX", person.toString())
        Log.d("YYY", pose2Char.pose2Char(hands).toString())
        surfaceHolder?.let {
            val canvas: Canvas = it.lockCanvas()
            try {
                stickman.drawStickman(
                    canvas,
                    pencilCase,
                    hands
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
