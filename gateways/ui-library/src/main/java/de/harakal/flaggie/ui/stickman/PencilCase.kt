package de.harakal.flaggie.ui.stickman

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

data class PencilCase(
    val borderWidth: Float = 3.0f,
    val handWidth: Float = 3.0f,
    val paint: Paint = Paint(),
    val mouthPath: Path = Path(),
    val faceColor: Int = Color.YELLOW,
    val eyesColor: Int = Color.BLACK,
    val mouthColor: Int = Color.BLACK,
    val borderColor: Int = Color.BLACK,
    val handColor: Int = Color.RED,
    val handColorLeft: Int = Color.BLUE
)