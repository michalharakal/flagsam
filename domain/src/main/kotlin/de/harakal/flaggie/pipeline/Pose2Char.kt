package de.harakal.flaggie.pipeline

import de.harakal.flaggie.ml.Hands
import kotlin.math.abs

class Pose2CharStep : Step<Hands, Char?> {
    override fun process(hands: Hands): Char? {
        return chars.firstOrNull {
            abs(it.hands.leftHand - hands.leftHand) < 10.0f && abs(it.hands.rightHand - hands.rightHand) < 10.0f
        }?.char
    }

    data class CharAngles(val hands: Hands, val char: Char)

    private val chars = listOf(
        CharAngles(Hands(leftHand = 90.0f, rightHand = 145.0f), 'a'),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 0.0f), 'b'),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 225.0f), 'c')
    )
}