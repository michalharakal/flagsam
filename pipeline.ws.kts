fun add(x: Int): Int = x + 1
fun mul(x: Int): String = "${x * 12}"

class Pipe<out A>(val v: A) {
    inline infix fun <B> andThen(function: (A) -> B) = Pipe(function(v))
}

interface Step<I, out B> {
    fun process(input: I): B
}

class Pose2CaharStep : Step<Int, Char> {
    override fun process(input:Int) = 'c'
}

class Pose2CaharSdtep : Step<Char, String> {
    override fun process(input: Char) = "c"
}


infix fun <T, R> T.into(other: (T) -> R) = Pipe(other(this))

print("2")
5 into Pose2CaharStep()::process andThen Pose2CaharSdtep()::process andThen ::println
