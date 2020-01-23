package de.harakal.flaggie.android.live

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import de.harakal.flaggie.android.R
import de.harakal.flaggie.android.pipeline.HandsPainter
import de.harakal.flaggie.android.pipeline.ScaledBitmap2Person
import de.harakal.flaggie.android.pipeline.TextViewLetterPrinter
import de.harakal.flaggie.bitmap.AssetBitmapProvider
import de.harakal.flaggie.bitmap.BitmapUtils
import de.harakal.flaggie.camera.LiveCameraPreview
import de.harakal.flaggie.ml.TensorflowLitePoseEstimator
import de.harakal.flaggie.pipeline.*
import de.harakal.flaggie.ui.stickman.PencilCase
import de.harakal.flaggie.ui.stickman.Stickman

const val MODEL_WIDTH = 257
const val MODEL_HEIGHT = 257

private const val REQUEST_CODE_PERMISSIONS = 10

/**
 * Activity shows cartoon like live view from Camera
 */
class LiveViewActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private lateinit var scaledBitmap2Person: ScaledBitmap2Person
    private lateinit var person2Hand: Person2Hands
    private lateinit var hands2Letter: Pose2CharStep
    private lateinit var letter2Printer: TextViewLetterPrinter
    private lateinit var handsPainter: HandsPainter
    private lateinit var cropBitmap: CropBitmap
    private lateinit var scaleBitmap: ScaleBitmap

    private val pencilCase = PencilCase()

    // This is an array of all the permission specified in the manifest.
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    private var surfaceHolder: SurfaceHolder? = null
    private lateinit var surfaceView: SurfaceView
    private lateinit var letters: TextView
    private lateinit var liveCameraPreview: LiveCameraPreview

    private val stickman = Stickman()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_view)
        surfaceView = findViewById(R.id.surfaceView)
        letters = findViewById(R.id.letters)
        surfaceView.holder.addCallback(this)
        val posenet = TensorflowLitePoseEstimator(this.applicationContext)
        liveCameraPreview = LiveCameraPreview(this) { bitmap -> processImage(bitmap) }

        createPipeLine(posenet)

        if (allPermissionsGranted()) {
            //surfaceView.post { liveCameraPreview.openCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun createPipeLine(posenet: TensorflowLitePoseEstimator) {
        scaledBitmap2Person = ScaledBitmap2Person(posenet)
        person2Hand = Person2Hands()
        hands2Letter = Pose2CharStep()
        letter2Printer = TextViewLetterPrinter(letters)
        handsPainter = HandsPainter(stickman, pencilCase)
        cropBitmap = CropBitmap(MODEL_HEIGHT, MODEL_WIDTH)
        scaleBitmap = ScaleBitmap(MODEL_HEIGHT, MODEL_WIDTH)
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
        val personPipeline =
            bitmap into cropBitmap::process andThen scaleBitmap::process andThen scaledBitmap2Person::process
        val handsPipeline = personPipeline andThen person2Hand::process andThen handsPainter::process

        handsPipeline andThen hands2Letter::process andThen letter2Printer::process
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        this.surfaceHolder = holder
        handsPainter.surfaceHolder = holder
        loadImageFromAsset()
    }
}
