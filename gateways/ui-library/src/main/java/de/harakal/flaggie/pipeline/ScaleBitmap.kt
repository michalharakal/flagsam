package de.harakal.flaggie.pipeline

import android.graphics.Bitmap
import de.harakal.flaggie.bitmap.BitmapUtils

class CropBitmap(private val modelHeight: Int, val modelWidth: Int) : Step<Bitmap, Bitmap> {
    override fun process(input: Bitmap): Bitmap =
        BitmapUtils.cropBitmap(input, modelHeight.toFloat(), modelWidth.toFloat())
}

class ScaleBitmap(private val modelHeight: Int, val modelWidth: Int) : Step<Bitmap, Bitmap> {
    override fun process(input: Bitmap): Bitmap =
        Bitmap.createScaledBitmap(input, modelHeight, modelHeight, true)
}
