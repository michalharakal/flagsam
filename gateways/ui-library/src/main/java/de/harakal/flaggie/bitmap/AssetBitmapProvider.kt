package de.harakal.flaggie.bitmap

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.io.InputStream

/**
 * Object for loading prepared images with poses from assets
 */
object AssetBitmapProvider {
    fun getBitmapFromAsset(context: Context, filePath: String): Bitmap? {
        val assetManager: AssetManager = context.getAssets()
        val istr: InputStream = assetManager.open(filePath)
        try {
            return BitmapFactory.decodeStream(istr)
        } catch (e: IOException) { // handle exception
            return null
        }
    }
}