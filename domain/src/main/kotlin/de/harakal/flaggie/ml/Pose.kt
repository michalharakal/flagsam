package de.harakal.flaggie.ml

/**
 * Enumrates body parts
 */
enum class BodyPart {
    NOSE,
    LEFT_EYE,
    RIGHT_EYE,
    LEFT_EAR,
    RIGHT_EAR,
    LEFT_SHOULDER,
    RIGHT_SHOULDER,
    LEFT_ELBOW,
    RIGHT_ELBOW,
    LEFT_WRIST,
    RIGHT_WRIST,
    LEFT_HIP,
    RIGHT_HIP,
    LEFT_KNEE,
    RIGHT_KNEE,
    LEFT_ANKLE,
    RIGHT_ANKLE
}

/**
 * Coordinates for a 2d Position
 */
data class Position (
    val x: Int = 0,
    val y: Int = 0
)

/**
 * Keypoint of body parts with probability score
 */
data class KeyPoint(
    val bodyPart: BodyPart = BodyPart.NOSE,
    val position: Position = Position(),
    val score: Float = 0.0f
)

/**
 * Keypoints for a person
 */
data class Person(val keyPoints: List<KeyPoint> = listOf(), val score: Float = 0.0f)
