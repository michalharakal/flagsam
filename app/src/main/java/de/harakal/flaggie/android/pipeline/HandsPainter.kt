package de.harakal.flaggie.android.pipeline

import android.graphics.Canvas
import android.view.SurfaceHolder
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.pipeline.Step
import de.harakal.flaggie.ui.stickman.PencilCase
import de.harakal.flaggie.ui.stickman.Stickman

class HandsPainter(private val stickman: Stickman, private val pencilCase: PencilCase) : Step<Hands, Hands> {
    var surfaceHolder: SurfaceHolder? = null

    override fun process(input: Hands): Hands {
        surfaceHolder?.let { holder ->
            val canvas: Canvas = holder.lockCanvas()
            try {
                stickman.drawStickman(
                    canvas,
                    pencilCase,
                    input
                )
            } finally {
                holder.unlockCanvasAndPost(canvas)
            }
        }
        return input
    }
}