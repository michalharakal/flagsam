plugins {
    `kotlin-dsl`
    `maven-publish`
}

val kotlinVersion = "1.3.50"

repositories {
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

group = "de.harakal.flaggie.gradle.plugin"
version = "1.0"

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:3.5.2")
    implementation("com.squareup.okhttp3:okhttp:4.2.1")
}


gradlePlugin {
    plugins {
        register("ModelDownloader") {
            id = "flaggie-plugin"
            implementationClass = "de.harakal.flaggie.gradle.FlaggiePlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

repositories {
    jcenter()
}

configurations.all {
    val isKotlinCompiler = name == "embeddedKotlin" ||
            name.startsWith("kotlin") ||
            name.startsWith("kapt")
    if (!isKotlinCompiler) {
        resolutionStrategy.eachDependency {
            @Suppress("UnstableApiUsage")
            if (requested.group == "org.jetbrains.kotlin" &&
                requested.module.name == "kotlin-compiler-embeddable"
            ) useVersion(kotlinVersion)
        }
    }
}