package de.harakal.flaggie.ml

import android.graphics.Bitmap
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Helper class converting bitmap into byte array with normalized values
 */
object BitmapConverter {
    /**
     * Converts bitmap into byte array
     */
    fun initInputArrayFromBitmap(bitmap: Bitmap):ByteBuffer {
        val bytesPerChannel = 4
        val inputChannels = 3
        val batchSize = 1
        val inputBuffer = ByteBuffer.allocateDirect(
            batchSize * bytesPerChannel * bitmap.height * bitmap.width * inputChannels
        )
        inputBuffer.order(ByteOrder.nativeOrder())
        inputBuffer.rewind()

        val mean = 128.0f
        val std = 128.0f
        for (row in 0 until bitmap.height) {
            for (col in 0 until bitmap.width) {
                val pixelValue = bitmap.getPixel(col, row)
                inputBuffer.putFloat(((pixelValue shr 16 and 0xFF) - mean) / std)
                inputBuffer.putFloat(((pixelValue shr 8 and 0xFF) - mean) / std)
                inputBuffer.putFloat(((pixelValue and 0xFF) - mean) / std)
            }
        }
        return inputBuffer
    }
}