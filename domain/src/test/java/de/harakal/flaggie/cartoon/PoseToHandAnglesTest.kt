package de.harakal.flaggie.cartoon

import assertk.assertThat
import assertk.assertions.isCloseTo
import assertk.assertions.isEqualTo
import de.harakal.flaggie.ml.BodyPart
import de.harakal.flaggie.ml.Position

import de.harakal.flaggie.ml.KeyPoint
import de.harakal.flaggie.ml.Person
import org.junit.Test

class PoseToHandAnglesTest {

    @Test
    fun char_a_left_hand() {
        val angles = PoseToHandAngles().poseToHands(A_a())
        assertThat(angles.leftHand).isEqualTo(90.0f)
    }

    @Test
    fun char_a_right_hand() {
        val angles = PoseToHandAngles().poseToHands(A_a())
        assertThat(angles.rightHand).isCloseTo(143.53076f, 0.001f)
    }

    @Test
    fun char_g_left_hand() {
        val angles = PoseToHandAngles().poseToHands(A_g())
        assertThat(angles.leftHand).isEqualTo(0.0f)
    }

    @Test
    fun char_g_right_hand() {
        val angles = PoseToHandAngles().poseToHands(A_g())
        assertThat(angles.rightHand).isEqualTo(97.2532f)
    }

    @Test
    fun char_c_left_hand() {
        val angles = PoseToHandAngles().poseToHands(A_c())
        assertThat(angles.leftHand).isEqualTo(93.63295f)
    }

    @Test
    fun char_c_right_hand() {
        val angles = PoseToHandAngles().poseToHands(A_c())
        assertThat(angles.rightHand).isEqualTo(226.36392f)
    }

    @Test
    fun char_e_left_hand() {
        val angles = PoseToHandAngles().poseToHands(A_e())
        assertThat(angles.leftHand).isEqualTo(309.14398f)
    }

    @Test
    fun char_e_right_hand() {
        val angles = PoseToHandAngles().poseToHands(A_e())
        assertThat(angles.rightHand).isEqualTo(97.38604f)
    }

    private fun A_a(): Person {
        return Person(
            keyPoints = listOf(
                KeyPoint(bodyPart = BodyPart.NOSE, position = Position(x = 130, y = 92), score = 0.9996445F),
                KeyPoint(bodyPart = BodyPart.LEFT_EYE, position = Position(x = 135, y = 87), score = 0.99782014F),
                KeyPoint(bodyPart = BodyPart.RIGHT_EYE, position = Position(x = 125, y = 87), score = 0.99785715F),
                KeyPoint(bodyPart = BodyPart.LEFT_EAR, position = Position(x = 141, y = 90), score = 0.85459507F),
                KeyPoint(bodyPart = BodyPart.RIGHT_EAR, position = Position(x = 118, y = 89), score = 0.8390062F),
                KeyPoint(bodyPart = BodyPart.LEFT_SHOULDER, position = Position(x = 147, y = 118), score = 0.9794973F),
                KeyPoint(bodyPart = BodyPart.RIGHT_SHOULDER, position = Position(x = 111, y = 116), score = 0.8000066F),
                KeyPoint(bodyPart = BodyPart.LEFT_ELBOW, position = Position(x = 155, y = 145), score = 0.9789494F),
                KeyPoint(bodyPart = BodyPart.RIGHT_ELBOW, position = Position(x = 92, y = 134), score = 0.97609895F),
                KeyPoint(bodyPart = BodyPart.LEFT_WRIST, position = Position(x = 147, y = 177), score = 0.97746223F),
                KeyPoint(bodyPart = BodyPart.RIGHT_WRIST, position = Position(x = 65, y = 150), score = 0.9645568F),
                KeyPoint(bodyPart = BodyPart.LEFT_HIP, position = Position(x = 138, y = 179), score = 0.96713823F),
                KeyPoint(bodyPart = BodyPart.RIGHT_HIP, position = Position(x = 113, y = 177), score = 0.88086826F),
                KeyPoint(bodyPart = BodyPart.LEFT_KNEE, position = Position(x = 134, y = 232), score = 0.9836791F),
                KeyPoint(bodyPart = BodyPart.RIGHT_KNEE, position = Position(x = 107, y = 232), score = 0.90542746F),
                KeyPoint(bodyPart = BodyPart.LEFT_ANKLE, position = Position(x = 134, y = 263), score = 0.81935906F),
                KeyPoint(bodyPart = BodyPart.RIGHT_ANKLE, position = Position(x = 113, y = 263), score = 0.53424627F)
            ),
            score = 0.90918887f
        )
    }

    private fun A_g() = Person(
        keyPoints = listOf(
            KeyPoint(bodyPart = BodyPart.NOSE, position = Position(x = 107, y = 67), score = 0.9981713F),
            KeyPoint(bodyPart = BodyPart.LEFT_EYE, position = Position(x = 112, y = 63), score = 0.98912907F),
            KeyPoint(bodyPart = BodyPart.RIGHT_EYE, position = Position(x = 103, y = 63), score = 0.99864346F),
            KeyPoint(bodyPart = BodyPart.LEFT_EAR, position = Position(x = 118, y = 70), score = 0.6584623F),
            KeyPoint(bodyPart = BodyPart.RIGHT_EAR, position = Position(x = 97, y = 67), score = 0.9020783F),
            KeyPoint(bodyPart = BodyPart.LEFT_SHOULDER, position = Position(x = 129, y = 89), score = 0.99698263F),
            KeyPoint(bodyPart = BodyPart.RIGHT_SHOULDER, position = Position(x = 87, y = 95), score = 0.99859065F),
            KeyPoint(bodyPart = BodyPart.LEFT_ELBOW, position = Position(x = 161, y = 91), score = 0.960477F),
            KeyPoint(bodyPart = BodyPart.RIGHT_ELBOW, position = Position(x = 82, y = 121), score = 0.9608246F),
            KeyPoint(bodyPart = BodyPart.LEFT_WRIST, position = Position(x = 190, y = 89), score = 0.91235363F),
            KeyPoint(bodyPart = BodyPart.RIGHT_WRIST, position = Position(x = 80, y = 150), score = 0.9804653F),
            KeyPoint(bodyPart = BodyPart.LEFT_HIP, position = Position(x = 116, y = 152), score = 0.9861967F),
            KeyPoint(bodyPart = BodyPart.RIGHT_HIP, position = Position(x = 92, y = 153), score = 0.99666804F),
            KeyPoint(bodyPart = BodyPart.LEFT_KNEE, position = Position(x = 114, y = 199), score = 0.95077693F),
            KeyPoint(bodyPart = BodyPart.RIGHT_KNEE, position = Position(x = 96, y = 204), score = 0.99569595F),
            KeyPoint(bodyPart = BodyPart.LEFT_ANKLE, position = Position(x = 107, y = 247), score = 0.93448424F),
            KeyPoint(bodyPart = BodyPart.RIGHT_ANKLE, position = Position(x = 94, y = 249), score = 0.9521582F)
        ), score = 0.95130336F
    )

    private fun A_c() = Person(
        keyPoints = listOf(
            KeyPoint(bodyPart = BodyPart.NOSE, position = Position(x = 127, y = 66), score = 0.99978405f),
            KeyPoint(bodyPart = BodyPart.LEFT_EYE, position = Position(x = 131, y = 61), score = 0.9975374f),
            KeyPoint(bodyPart = BodyPart.RIGHT_EYE, position = Position(x = 123, y = 61), score = 0.9984503f),
            KeyPoint(bodyPart = BodyPart.LEFT_EAR, position = Position(x = 138, y = 65), score = 0.8118424f),
            KeyPoint(bodyPart = BodyPart.RIGHT_EAR, position = Position(x = 114, y = 64), score = 0.817331f),
            KeyPoint(bodyPart = BodyPart.LEFT_SHOULDER, position = Position(x = 142, y = 91), score = 0.98655415f),
            KeyPoint(bodyPart = BodyPart.RIGHT_SHOULDER, position = Position(x = 105, y = 82), score = 0.93621415f),
            KeyPoint(bodyPart = BodyPart.LEFT_ELBOW, position = Position(x = 148, y = 124), score = 0.9902776f),
            KeyPoint(bodyPart = BodyPart.RIGHT_ELBOW, position = Position(x = 82, y = 60), score = 0.86470735f),
            KeyPoint(bodyPart = BodyPart.LEFT_WRIST, position = Position(x = 138, y = 154), score = 0.96405756f),
            KeyPoint(bodyPart = BodyPart.RIGHT_WRIST, position = Position(x = 62, y = 41), score = 0.95230556f),
            KeyPoint(bodyPart = BodyPart.LEFT_HIP, position = Position(x = 129, y = 152), score = 0.99746966f),
            KeyPoint(bodyPart = BodyPart.RIGHT_HIP, position = Position(x = 108, y = 149), score = 0.9520895f),
            KeyPoint(bodyPart = BodyPart.LEFT_KNEE, position = Position(x = 126, y = 200), score = 0.99174654f),
            KeyPoint(bodyPart = BodyPart.RIGHT_KNEE, position = Position(x = 113, y = 201), score = 0.9594184f),
            KeyPoint(bodyPart = BodyPart.LEFT_ANKLE, position = Position(x = 124, y = 245), score = 0.90580946f),
            KeyPoint(bodyPart = BodyPart.RIGHT_ANKLE, position = Position(x = 116, y = 247), score = 0.849956f)
        ), score = 0.93973833f
    )

    private fun A_e() = Person(
        keyPoints = listOf(
            KeyPoint(bodyPart = BodyPart.NOSE, position = Position(x = 110, y = 66), score = 0.99127495f),
            KeyPoint(bodyPart = BodyPart.LEFT_EYE, position = Position(x = 115, y = 64), score = 0.98928237f),
            KeyPoint(bodyPart = BodyPart.RIGHT_EYE, position = Position(x = 107, y = 63), score = 0.9966893f),
            KeyPoint(bodyPart = BodyPart.LEFT_EAR, position = Position(x = 123, y = 69), score = 0.7334115f),
            KeyPoint(bodyPart = BodyPart.RIGHT_EAR, position = Position(x = 101, y = 67), score = 0.93477464f),
            KeyPoint(bodyPart = BodyPart.LEFT_SHOULDER, position = Position(x = 132, y = 88), score = 0.9932393f),
            KeyPoint(bodyPart = BodyPart.RIGHT_SHOULDER, position = Position(x = 89, y = 96), score = 0.99930394f),
            KeyPoint(bodyPart = BodyPart.LEFT_ELBOW, position = Position(x = 153, y = 71), score = 0.905739f),
            KeyPoint(bodyPart = BodyPart.RIGHT_ELBOW, position = Position(x = 82, y = 125), score = 0.9699976f),
            KeyPoint(bodyPart = BodyPart.LEFT_WRIST, position = Position(x = 175, y = 53), score = 0.8649771f),
            KeyPoint(bodyPart = BodyPart.RIGHT_WRIST, position = Position(x = 82, y = 150), score = 0.97724015f),
            KeyPoint(bodyPart = BodyPart.LEFT_HIP, position = Position(x = 122, y = 149), score = 0.98731184f),
            KeyPoint(bodyPart = BodyPart.RIGHT_HIP, position = Position(x = 95, y = 154), score = 0.9948907f),
            KeyPoint(bodyPart = BodyPart.LEFT_KNEE, position = Position(x = 119, y = 201), score = 0.9361535f),
            KeyPoint(bodyPart = BodyPart.RIGHT_KNEE, position = Position(x = 98, y = 206), score = 0.98412216f),
            KeyPoint(bodyPart = BodyPart.LEFT_ANKLE, position = Position(x = 103, y = 251), score = 0.90632355f),
            KeyPoint(bodyPart = BodyPart.RIGHT_ANKLE, position = Position(x = 103, y = 251), score = 0.8822133f)
        ),
        score = 0.94393796f
    )
}