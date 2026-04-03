plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

group = "de.harakal.flaggie.gradle.plugin"
version = "1.0"

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:8.9.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.7")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.2")
}

gradlePlugin {
    plugins {
        register("ModelDownloader") {
            id = "flaggie-plugin"
            implementationClass = "de.harakal.flaggie.gradle.FlaggiePlugin"
        }
    }
}
