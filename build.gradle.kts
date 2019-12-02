import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        maven { setUrl( "https://maven.fabric.io/public") }


    }
    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.kotlinSerializationGradlePlugin)
        classpath ("io.spring.gradle:dependency-management-plugin:1.0.7.RELEASE")
        classpath ("io.fabric.tools:gradle:1.29.0")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.register("clean").configure {
    delete("build")
}

