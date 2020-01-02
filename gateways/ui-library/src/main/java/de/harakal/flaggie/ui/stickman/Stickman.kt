package de.harakal.flaggie.ui.stickman

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import de.harakal.flaggie.ml.Hands
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/**
 * Stateless class responsible for drawing a stickman with given angles for hands
 */
class Stickman {

    fun drawStickman(canvas: Canvas, pencilCase: PencilCase, hands: Hands) {
        val screenWidth: Int = canvas.width
        val screenHeight: Int = canvas.height
        var size = min(screenHeight, screenWidth).toFloat() / 2.0f

        canvas.drawColor(Color.BLACK)
        drawFaceBackground(canvas, pencilCase, size)
        drawEyes(canvas, pencilCase, size)
        drawMouth(canvas, pencilCase, size)
        drawBody(canvas, pencilCase, size)
        drawLeft(canvas, pencilCase, hands.leftHand, size)
        drawRight(canvas, pencilCase, hands.rightHand, size)
        Log.d("YYY", "left=${hands.leftHand}, right=${hands.rightHand}")
    }

    private fun drawBody(canvas: Canvas, pencilCase: PencilCase, size: Float) {
        pencilCase.paint.color = pencilCase.bodyColor
        pencilCase.paint.style = Paint.Style.STROKE
        pencilCase.paint.strokeWidth = pencilCase.bodyWidth

        canvas.drawLine(
            size / 2.0f,
            size,
            size / 2.0f,
            size / 2.0f + size + 30.0f + 40.0f,
            pencilCase.paint
        )

    }

    private fun drawLeft(canvas: Canvas, pencilCase: PencilCase, angle: Float, size: Float) {
        pencilCase.paint.color = pencilCase.handColorLeft
        pencilCase.paint.style = Paint.Style.STROKE
        pencilCase.paint.strokeWidth = pencilCase.handWidth

        val handX = (size / 2.0f) * cos((angle * PI / 180))
        val handY = (size / 2.0f) * sin((angle * PI / 180))
        canvas.drawLine(
            size / 2.0f,
            size + 30.0f,
            size / 2.0f + handX.toFloat(),
            size + 20.0f + handY.toFloat(),
            pencilCase.paint
        )

    }

    private fun drawRight(canvas: Canvas, pencilCase: PencilCase, angle: Float, size: Float) {
        pencilCase.paint.color = pencilCase.handColor
        pencilCase.paint.style = Paint.Style.STROKE
        pencilCase.paint.strokeWidth = pencilCase.handWidth

        val armLength = size/2
        val angleRad = ((angle - 90) * PI) / 180
        val dX = sin(angleRad) * armLength
        val dY = cos(angleRad) * armLength
        canvas.drawLine(
            size / 2.0f,
            size + 20.0f,
            size / 2.0f - dX.toFloat(),
            size + 20.0f + dY.toFloat(),
            pencilCase.paint
        )
    }

    private fun drawFaceBackground(canvas: Canvas, pencilCase: PencilCase, size: Float) {
        pencilCase.paint.color = pencilCase.faceColor
        pencilCase.paint.style = Paint.Style.FILL
        val radius = size / 2f
        canvas.drawCircle(size / 2f, size / 2f, radius, pencilCase.paint)
        pencilCase.paint.color = pencilCase.borderColor
        pencilCase.paint.style = Paint.Style.STROKE
        pencilCase.paint.strokeWidth = pencilCase.borderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - pencilCase.borderWidth / 2f, pencilCase.paint)
    }

    private fun drawEyes(canvas: Canvas, pencilCase: PencilCase, size: Float) {
        pencilCase.paint.color = pencilCase.eyesColor
        pencilCase.paint.style = Paint.Style.FILL
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas.drawOval(leftEyeRect, pencilCase.paint)
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas.drawOval(rightEyeRect, pencilCase.paint)
    }

    private fun drawMouth(canvas: Canvas, pencilCase: PencilCase, size: Float) {
        pencilCase.mouthPath.moveTo(size * 0.22f, size * 0.7f)
        pencilCase.mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
        pencilCase.mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
        pencilCase.paint.color = pencilCase.mouthColor
        pencilCase.paint.style = Paint.Style.FILL
        canvas.drawPath(pencilCase.mouthPath, pencilCase.paint)
    }
}