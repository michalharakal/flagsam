plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/LICENSE")
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/common.kotlin_module")
        exclude("META-INF/*.kotlin_module")
    }
    aaptOptions {
        noCompress("tflite")
        noCompress ("lite")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":gateways:android-tensorflow"))
    implementation(project(":gateways:ui-library"))

    implementation ("org.tensorflow:tensorflow-lite:2.0.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.0.0")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.0.0")

    implementation(Libraries.kotlinStdLib)
    implementation("org.slf4j:slf4j-api:1.7.28")
    implementation("com.github.tony19:logback-android:1.3.0-3")

    testImplementation(Libraries.kotlinTestJvm)
    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.assertj:assertj-core:3.13.2")
    testImplementation("org.robolectric:robolectric:4.3")
}
