package de.harakal.flaggie.android

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class StickmanView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angleLeft: Float = 0.0f
    var angleRight: Float = 0.0f

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320
    private val mouthPath = Path()
    private val bodyPath = Path()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = getMeasuredWidth() / 5
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth())
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
        val handX = (size / 2.0f) * cos((angle * PI / 180))
        val handY = (size / 2.0f) * sin((angle * PI / 180))
        canvas.drawLine(
            size / 2.0f, size.toFloat() + 30.0f,
            size / 2.0f + handX.toFloat(), size.toFloat() + 30.0f + handY.toFloat(), paint
        )

    }

    private fun drawRight(canvas: Canvas, angle: Float) {
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