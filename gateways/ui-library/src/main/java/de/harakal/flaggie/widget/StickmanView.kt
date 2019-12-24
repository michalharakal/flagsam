package de.harakal.flaggie.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** Custom view showing Stickman with flag alphabet pose derfined by angles for every hand */
class StickmanView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angleLeft: Float = (0..180).random().toFloat()
    var angleRight: Float = (0..180).random().toFloat()

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    private var handColor = Color.DKGRAY
    private var handColorLeft = Color.BLUE

    // Face border width in pixels
    private var borderWidth = 4.0f
    private var handWidth = 14.0f

    // View size in pixels
    private var size = 320
    private val mouthPath = Path()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth / 5
        setMeasuredDimension(measuredWidth, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
        drawBody(canvas)
        drawLeft(canvas, angleLeft)
        drawRight(canvas, angleRight)
    }

    private fun drawBody(canvas: Canvas) {
        canvas.drawLine(size / 2.0f, size.toFloat(), size / 2.0f, size * 3.0f, paint)
    }

    private fun drawLeft(canvas: Canvas, angle: Float) {
        paint.color = handColorLeft
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = handWidth

        val handX = (size / 2.0f) * cos((angle * PI / 180))
        val handY = (size / 2.0f) * sin((angle * PI / 180))
        canvas.drawLine(
            size / 2.0f, size.toFloat() + 30.0f,
            size / 2.0f + handX.toFloat(), size.toFloat() + 30.0f + handY.toFloat(), paint
        )

    }

    private fun drawRight(canvas: Canvas, angle: Float) {
        paint.color = handColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = handWidth

        val handX = (size / 2.0f) * cos((angle * PI / 180))
        val handY = (size / 2.0f) * sin((angle * PI / 180))
        canvas.drawLine(
            size / 2.0f, size.toFloat() + 30.0f,
            size / 2.0f - handX.toFloat(), size.toFloat() + 30.0f + handY.toFloat(), paint
        )
    }

    private fun drawFaceBackground(canvas: Canvas) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL
        val radius = size / 2f
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        paint.color = eyesColor
        paint.style = Paint.Style.FILL
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas.drawOval(leftEyeRect, paint)
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
        paint.color = mouthColor
        paint.style = Paint.Style.FILL
        canvas.drawPath(mouthPath, paint)
    }
}