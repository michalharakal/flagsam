plugins {
    id ("com.android.library")
    id (BuildPlugins.kotlinAndroidExtensions)
    id("kotlin-kapt")
    id("flaggie-plugin")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)

    implementation("org.slf4j:slf4j-api:1.7.28")
    implementation ("com.github.tony19:logback-android:1.3.0-3")
    implementation ("org.tensorflow:tensorflow-lite:2.0.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.0.0")


    testImplementation(Libraries.kotlinTestJvm)
    testImplementation(Libraries.kotlinxCoroutinesCore)
    testImplementation(Libraries.kotlinTestJunit)
}
