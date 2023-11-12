plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.core_view"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures.compose = true
}

dependencies {
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.6.0-alpha08")
    implementation(Dependencies.JetpackCompose.material)
    implementation(Dependencies.Data.ktorJson)
    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.composeNavigation)
    implementation(Dependencies.JetpackCompose.runtime)
    implementation(Dependencies.Android.lifecycle)
}
