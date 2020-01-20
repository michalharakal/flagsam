package de.harakal.flaggie.pipeline

import de.harakal.flaggie.cartoon.PoseToHandAngles
import de.harakal.flaggie.ml.Hands
import de.harakal.flaggie.ml.Person

class Person2Hands : Step<Person, Hands> {

    private val handAngles = PoseToHandAngles()

    override fun process(input: Person) = handAngles.poseToHands(input)
}
