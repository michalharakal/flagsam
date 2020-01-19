package de.harakal.flaggie.pipeline

import com.sun.jdi.CharType
import de.harakal.flaggie.ml.ControlSign
import de.harakal.flaggie.ml.FlagChar
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.ml.SignType
import kotlin.math.abs

class Pose2CharStep : Step<Hands, Char?> {
    override fun process(hands: Hands): Char? {
        return chars.firstOrNull {
            abs(it.hands.leftHand - hands.leftHand) < 10.0f
                    && abs(it.hands.rightHand - hands.rightHand) < 10.0f
                    && (it.charType.signType == SignType.CHAR)
        }?.char
    }

    data class CharAngles(
        val hands: Hands,
        val char: Char = '#',
        val charType: FlagChar = FlagChar(SignType.CHAR, ControlSign.NONE)
    )

    // Angles when facing the signal person - left is 0, down is 90, right is 180, up is 270
    private val chars = listOf(
        CharAngles(Hands(leftHand = 90.0f, rightHand = 135.0f), 'a'),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 180.0f), 'b'),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 225.0f), 'c'),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 270.0f), 'd'),
        CharAngles(Hands(leftHand = 315.0f, rightHand = 90.0f), 'e'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 90.0f), 'f'),
        CharAngles(Hands(leftHand = 45.0f, rightHand = 90.0f), 'g'),
        CharAngles(Hands(leftHand = 135.0f, rightHand = 180.0f), 'h'),
        CharAngles(Hands(leftHand = 135.0f, rightHand = 225.0f), 'i'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 270.0f), 'j'),
        CharAngles(Hands(leftHand = 270.0f, rightHand = 135.0f), 'k'),
        CharAngles(Hands(leftHand = 315.0f, rightHand = 135.0f), 'l'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 135.0f), 'm'),
        CharAngles(Hands(leftHand = 45.0f, rightHand = 135.0f), 'n'),
        CharAngles(Hands(leftHand = 225.0f, rightHand = 180.0f), 'o'),
        CharAngles(Hands(leftHand = 270.0f, rightHand = 180.0f), 'p'),
        CharAngles(Hands(leftHand = 315.0f, rightHand = 180.0f), 'q'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 180.0f), 'r'),
        CharAngles(Hands(leftHand = 45.0f, rightHand = 180.0f), 's'),
        CharAngles(Hands(leftHand = 270.0f, rightHand = 225.0f), 't'),
        CharAngles(Hands(leftHand = 315.0f, rightHand = 225.0f), 'u'),
        CharAngles(Hands(leftHand = 45.0f, rightHand = 270.0f), 'v'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 315.0f), 'w'),
        CharAngles(Hands(leftHand = 45.0f, rightHand = 315.0f), 'x'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 225.0f), 'y'),
        CharAngles(Hands(leftHand = 0.0f, rightHand = 45.0f), 'z'),
        CharAngles(
            Hands(leftHand = 90.0f, rightHand = 90.0f),
            charType = FlagChar(SignType.CONTROL, ControlSign.REST_SPACE)
        ),
        CharAngles(
            Hands(leftHand = 315.0f, rightHand = 270.0f),
            charType = FlagChar(SignType.CONTROL, ControlSign.NUMBER_TO_FOLLOW)
        ),
        CharAngles(Hands(leftHand = 90.0f, rightHand = 135.0f), '1'),     //=a
        CharAngles(Hands(leftHand = 90.0f, rightHand = 180.0f), '2'),     //=b
        CharAngles(Hands(leftHand = 90.0f, rightHand = 225.0f), '3'),     //=c
        CharAngles(Hands(leftHand = 90.0f, rightHand = 270.0f), '4'),     //=d
        CharAngles(Hands(leftHand = 315.0f, rightHand = 90.0f), '5'),     //=e
        CharAngles(Hands(leftHand = 0.0f, rightHand = 90.0f), '6'),       //=f
        CharAngles(Hands(leftHand = 45.0f, rightHand = 90.0f), '7'),      //=g
        CharAngles(Hands(leftHand = 135.0f, rightHand = 180.0f), '8'),    //=h
        CharAngles(Hands(leftHand = 135.0f, rightHand = 225.0f), '9'),    //=i
        CharAngles(Hands(leftHand = 270.0f, rightHand = 135.0f), '0'),    //=k
        CharAngles(
            Hands(leftHand = 0.0f, rightHand = 270.0f),
            charType = FlagChar(SignType.CONTROL, ControlSign.LETTER_TO_FOLOW)
        ),
        CharAngles(
            Hands(leftHand = 315.0f, rightHand = 225.0f),
            charType = FlagChar(SignType.CONTROL, ControlSign.ERROR_ATTENTION)
        ),
        CharAngles(
            Hands(leftHand = 45.0f, rightHand = 225.0f),
            charType = FlagChar(SignType.CONTROL, ControlSign.CANCEL)
        )
    )
}
