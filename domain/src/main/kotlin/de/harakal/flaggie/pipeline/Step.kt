package de.harakal.flaggie.pipeline

interface Step<I, O> {
    fun process(input: I): O
}