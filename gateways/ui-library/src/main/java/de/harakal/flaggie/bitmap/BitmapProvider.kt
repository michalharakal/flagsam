package de.harakal.flaggie.bitmap

import android.graphics.Bitmap

interface BitmapProvider {
    fun onNewBitmap(source:BitmapSource):Bitmap

    enum class BitmapSource {
        ASSETS,
        CAMERA,
        FRONT_CAMERA
    }
}