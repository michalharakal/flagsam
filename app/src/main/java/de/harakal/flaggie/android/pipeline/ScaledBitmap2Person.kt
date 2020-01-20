package de.harakal.flaggie.android.pipeline

import android.graphics.Bitmap
import de.harakal.flaggie.ml.Person
import de.harakal.flaggie.ml.TensorflowLitePoseEstimator
import de.harakal.flaggie.pipeline.Step

class ScaledBitmap2Person(private val posenet: TensorflowLitePoseEstimator) : Step<Bitmap, Person> {
    override fun process(input: Bitmap) = posenet.estimateSinglePose(input)
}