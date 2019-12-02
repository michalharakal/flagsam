plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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
}

dependencies {
    implementation(project(":semafornet"))
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.core:core-ktx:1.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation ("com.google.android.material:material:1.0.0")

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.kotlinxCoroutinesCore)
    implementation(Libraries.kotlinxSerializeJvm)
    implementation(Libraries.ktorUtilsJvm)
    implementation(Libraries.ktorCoreJvm)
    implementation(Libraries.ktorSerializationJvm)
    implementation(Libraries.ktorLoggingJvm)
    implementation(Libraries.ktorOkhttpJvm)
    implementation(Libraries.okhttInterceptorpJvm)
    implementation("org.slf4j:slf4j-api:1.7.28")
    implementation ("com.github.tony19:logback-android:1.3.0-3")

    testImplementation(Libraries.kotlinTestJvm)
    testImplementation(Libraries.kotlinxCoroutinesCore)
    testImplementation(Libraries.kotlinTestJunit)
}
