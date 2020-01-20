package de.harakal.flaggie.pipeline

class Pipe<out A>(val v: A) {
    inline infix fun <B> andThen(function: (A) -> B) = Pipe(function(v))
}

infix fun <T, R> T.into(other: (T) -> R) = Pipe(other(this))

