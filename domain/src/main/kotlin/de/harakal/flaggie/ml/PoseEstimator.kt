package de.harakal.flaggie.ml

import java.nio.ByteBuffer

interface PoseEstimator {

    /**
     * Estimates the pose for a single person.
     *
     * args:
     *      bitmapBuffer: normalized [ByteBuffer] with scaled image of [-1,1] values.
     *      width: image width
     *      height: image height
     * returns:
     *      person: a Person object containing data about keypoint locations and confidence scores
     */
    fun estimateSinglePose(width: Int, height: Int, bitmapBuffer: ByteBuffer): Person

}
