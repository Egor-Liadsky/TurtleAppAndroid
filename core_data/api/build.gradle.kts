plugins {
    id("turtleapp.library")
//    id("turtleapp.library.feature")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.example.api"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.ktor.serialization)
}