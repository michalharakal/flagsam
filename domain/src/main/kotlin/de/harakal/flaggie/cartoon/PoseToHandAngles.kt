package de.harakal.flaggie.cartoon

import de.harakal.flaggie.ml.BodyPart
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.ml.Person
import de.harakal.flaggie.ml.Position
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.sqrt

class PoseToHandAngles {
    fun poseToHands(person: Person): Hands {
        //val isFrontFace = isFrontFace(person)
        val leftCoordinates = getLeftHandCoordinates(person)
        val rightCoordinates = getRightHandCoordinates(person)
        return Hands(
            leftHand = angleLeftFromCoordinates(leftCoordinates = leftCoordinates),
            rightHand = angleRightFromCoordinates(rightCoordinates = rightCoordinates)
        )
    }

    private fun angleRightFromCoordinates(rightCoordinates: HandCoordinates): Float {
        val dx = abs(rightCoordinates.shoulder.x - rightCoordinates.wrist.x)
        val dy = abs(rightCoordinates.shoulder.y - rightCoordinates.wrist.y)
        if (abs( dy.toFloat()) < 0.001f) {
            return 180.0f
        }
        val sinAlfa = dx / sqrt((dx * dx + dy * dy).toDouble())
        val angleRad = asin(sinAlfa)
        return if (rightCoordinates.shoulder.y < rightCoordinates.wrist.y) {
            (angleRad * (180.0f / Math.PI)).toFloat() + 90
        } else {
            (angleRad * (180.0f / Math.PI)).toFloat() + 180
        }
    }

    private fun angleLeftFromCoordinates(leftCoordinates: HandCoordinates): Float {
        val dx = abs(leftCoordinates.shoulder.x - leftCoordinates.wrist.x)
        val dy = abs(leftCoordinates.shoulder.y - leftCoordinates.wrist.y)
        if (abs( dy.toFloat()) < 0.001f) {
            return 0.0f
        }
        val sinAlfa = dx / sqrt((dx * dx + dy * dy).toDouble())
        val angleRad = asin(sinAlfa)
        return if (leftCoordinates.shoulder.y < leftCoordinates.wrist.y) {
             (angleRad * (180.0f / Math.PI)).toFloat() + 90
        } else {
            360 - (angleRad * (180.0f / Math.PI)).toFloat()
        }
    }

    private fun getRightHandCoordinates(person: Person): HandCoordinates {
        val shoulder = getPosition(person, BodyPart.RIGHT_SHOULDER)
        val elbow = getPosition(person, BodyPart.RIGHT_ELBOW)
        val wrist = getPosition(person, BodyPart.RIGHT_WRIST)
        return HandCoordinates(shoulder, elbow, wrist)
    }

    private fun getLeftHandCoordinates(person: Person): HandCoordinates {
        val shoulder = getPosition(person, BodyPart.LEFT_SHOULDER)
        val elbow = getPosition(person, BodyPart.LEFT_ELBOW)
        val wrist = getPosition(person, BodyPart.LEFT_WRIST)
        return HandCoordinates(shoulder, elbow, wrist)
    }

    //private fun isFrontFace(person: Person) = isValid(person, BodyPart.NOSE)

    private fun isValid(person: Person, bodyPart: BodyPart): Boolean {
        val keyPoint = person.keyPoints.firstOrNull { entry -> entry.bodyPart == bodyPart }
        keyPoint?.let {
            return it.score > 0.5
        }
        return false
    }

    private fun getPosition(person: Person, bodyPart: BodyPart): Position {
        if (isValid(person, bodyPart)) {
            val keyPoint = person.keyPoints.firstOrNull { entry -> entry.bodyPart == bodyPart }
            keyPoint?.let {
                if (it.score > 0.5) {
                    return it.position
                }
            }
        }
        return Position(0, 0)
    }

    data class HandCoordinates(val shoulder: Position, val elbow: Position, val wrist: Position)
}