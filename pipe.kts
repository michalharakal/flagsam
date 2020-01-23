fun add(x: Int): Int = x + 1
fun mul(x: Int): Int = x * 12

class Pipe<out A>(val v: A) {
    inline infix fun <B> andThen(function: (A) -> B) = Pipe(function(v))
}

infix fun <T, R> T.into(other: (T) -> R) = Pipe(other(this))

fun main() {
    print("2")
    5 into ::add andThen ::mul andThen ::println
}