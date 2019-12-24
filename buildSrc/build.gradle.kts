plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.fabric.io/public")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}


group = "de.harakal.flaggie.gradle.plugin"
version = "1.0"


object PluginsVersions {
    const val GRADLE_ANDROID = "3.5.1"
    const val GRADLE_VERSIONS = "0.22.0"
    const val KOTLIN = "1.3.50"
    const val NAVIGATION = "2.1.0-beta02"
    const val JACOCO = "0.16.0-SNAPSHOT"
    const val FABRIC = "1.31.0"
    const val DOKKA = "0.10.0"
    const val KTLINT = "0.34.2"
    const val SPOTLESS = "3.24.1"
    const val DETEKT = "1.0.1"
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.squareup.okhttp3:okhttp:4.2.2")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.vanniktech:gradle-android-junit-jacoco-plugin:${PluginsVersions.JACOCO}")
    implementation("io.fabric.tools:gradle:${PluginsVersions.FABRIC}")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginsVersions.DOKKA}")
    implementation("com.pinterest:ktlint:${PluginsVersions.KTLINT}")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginsVersions.SPOTLESS}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}")
    implementation("guru.nidi:graphviz-java:0.12.1")
}

gradlePlugin {
    plugins {
        register("ModelDownloader") {
            id = "flaggie-plugin"
            implementationClass = "de.harakal.flaggie.gradle.FlaggiePlugin"
        }
    }
}