package de.harakal.flaggie.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.ui.stickman.RectSize

/**
 * Stateless class responsible for drawing a stickman with given angles for hands
 */
class Stickman {

    private var paint = Paint()

    private val minConfidence = 0.5

    private val circleRadius = 8.0f

    fun drawStickman(canvas: Canvas, hands: Hands, modelSize: RectSize) {
        val screenWidth: Int = canvas.width
        val screenHeight: Int = canvas.height
        setPaint()

        val widthRatio = screenWidth.toFloat() / modelSize.width
        val heightRatio = screenHeight.toFloat() / modelSize.height

        // Draw key points over the image.
        for (keyPoint in person.keyPoints) {
            if (keyPoint.score > minConfidence) {
                val position = keyPoint.position
                val adjustedX: Float = position.x.toFloat() * widthRatio
                val adjustedY: Float = position.y.toFloat() * heightRatio
                canvas.drawCircle(adjustedX, adjustedY, circleRadius, paint)
            }
        }

        for (line in bodyJoints) {
            if (
                (person.keyPoints[line.first.ordinal].score > minConfidence) and
                (person.keyPoints[line.second.ordinal].score > minConfidence)
            ) {
                canvas.drawLine(
                    person.keyPoints[line.first.ordinal].position.x.toFloat() * widthRatio,
                    person.keyPoints[line.first.ordinal].position.y.toFloat() * heightRatio,
                    person.keyPoints[line.second.ordinal].position.x.toFloat() * widthRatio,
                    person.keyPoints[line.second.ordinal].position.y.toFloat() * heightRatio,
                    paint
                )
            }
        }
    }

    /** Set the paint color and size.    */
    private fun setPaint() {
        paint.color = Color.RED
        paint.textSize = 80.0f
        paint.strokeWidth = 8.0f
    }
}