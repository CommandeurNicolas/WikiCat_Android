@file:OptIn(KspExperimental::class)

import com.google.devtools.ksp.KspExperimental
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Kotlin Symbol Processor (KSP)
    id("com.google.devtools.ksp")
    // Dagger Hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nicolascommandeur.wikicat"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.nicolascommandeur.wikicat"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Load API key from local.properties
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // * Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // * Room (database)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // * Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // * Gson
    implementation(libs.gson)

    // * Compose navigation
    implementation(libs.androidx.navigation.compose)
}

// ! Needed to align to Kotlin version
ksp {
    useKsp2 = true
}