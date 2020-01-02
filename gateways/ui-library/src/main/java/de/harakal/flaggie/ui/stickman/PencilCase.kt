package de.harakal.flaggie.ui.stickman

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

/**
 * Data class wrapping properties for painting a stickman.
 */
data class PencilCase(
    val borderWidth: Float = 3.0f,
    val handWidth: Float = 3.0f,
    val bodyWidth: Float = 13.0f,
    val paint: Paint = Paint(),
    val mouthPath: Path = Path(),
    val faceColor: Int = Color.YELLOW,
    val eyesColor: Int = Color.BLACK,
    val mouthColor: Int = Color.BLACK,
    val borderColor: Int = Color.BLACK,
    val handColor: Int = Color.RED,
    val bodyColor: Int = Color.GREEN,
    val handColorLeft: Int = Color.BLUE
)