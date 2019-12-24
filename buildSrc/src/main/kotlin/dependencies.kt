const val kotlinVersion = "1.3.61"

object RootBuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:3.5.2"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61"
    const val fabric = "io.fabric"
}

object AndroidSdk {
    const val min = 23
    const val compile = 29
    const val target = compile
    const val buildTool = "29.0.2"
}

object Libraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinTestJvm = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
}