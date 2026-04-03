plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("flaggie-plugin")
}

android {
    namespace = "de.harakal.flaggie.android_tensorflow"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.slf4j.api)
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.gpu)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    testImplementation(libs.assertj)
    testImplementation(libs.robolectric)
}
