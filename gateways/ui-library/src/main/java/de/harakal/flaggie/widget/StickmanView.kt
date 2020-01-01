package de.harakal.flaggie.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.ui.stickman.PencilCase
import de.harakal.flaggie.ui.stickman.Stickman

/** Custom view showing Stickman with flag alphabet pose derfined by angles for every hand */
class StickmanView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angleLeft: Float = (0..180).random().toFloat()
    var angleRight: Float = (0..180).random().toFloat()

    lateinit var pencilCase: PencilCase

    val stickman = Stickman()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //size = measuredWidth / 5
        setMeasuredDimension(measuredWidth, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)
        stickman.drawStickman(
            canvas = canvas,
            pencilCase = pencilCase,
            hands = Hands(angleLeft, angleRight)
        )
    }
}