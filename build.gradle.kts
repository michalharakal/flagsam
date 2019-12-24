import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("https://plugins.gradle.org/m2/")
     }
    dependencies {
        classpath (RootBuildPlugins.androidGradlePlugin)
        classpath (RootBuildPlugins.kotlinGradlePlugin)
        classpath ("io.spring.gradle:dependency-management-plugin:1.0.7.RELEASE")
        classpath ("io.fabric.tools:gradle:1.31.0")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    plugins.apply("plugins.git-hooks")
    plugins.apply("plugins.detekt")
    plugins.apply("plugins.ktlint")
    plugins.apply("plugins.spotless")
}

apply(from = "scripts/gradle/projectDependencyGraph.gradle")

