plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp") version "2.3.10"
}

android {
    namespace = "com.example.itmda_b4d4"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.itmda_b4d4"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    dataBinding{
        enable = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    implementation ("com.piesocket:channels-sdk:7.1.0")
    implementation ("com.google.android.material:material:1.14.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    val room_version = "3.0.3"

    implementation("androidx.room3:room3-runtime:$room_version")
    ksp("androidx.room3:room3-compiler:$room_version")
}