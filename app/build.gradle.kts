plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.exaple.nosnooze"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nosnooze"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceComit patibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
implementation ()

kotlin {
    jvmToolchain(17)
}