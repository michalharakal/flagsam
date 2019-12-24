plugins {
    id ("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libraries.kotlinStdLib)

    implementation("org.slf4j:slf4j-api:1.7.28")
    implementation ("org.tensorflow:tensorflow-lite:2.0.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.0.0")

    testImplementation(Libraries.kotlinTestJvm)
    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.assertj:assertj-core:3.13.2")
    testImplementation("org.robolectric:robolectric:4.3")
}
