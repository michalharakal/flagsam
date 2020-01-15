package de.harakal.flaggie.pipeline

import de.harakal.flaggie.pipeline.Step.StepException

class Pipeline<I, O> constructor(private val current: Step<I, O>) {

    fun <NewO> pipe(next: Step<I, NewO>): Pipeline<I, NewO> {
        return Pipeline(object : Step<I, NewO> {
            override fun process(input: I): NewO {
                return next.process(input)
            }
        })
    }

    @Throws(StepException::class)
    fun execute(input: I): O {
        return current.process(input)
    }

}