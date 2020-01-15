package de.harakal.flaggie.pipeline

interface Step<I, O> {
    class StepException(t: Throwable?) : RuntimeException(t)

    fun process(input: I): O
}