plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.turtleteam.impl"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":feature:teacher:api"))
    implementation(project(Modules.core_view))
    implementation(project(Modules.core_navigation))
    implementation(project(Modules.core_data))
    implementation(project(Modules.storage))

    implementation(Dependencies.Data.ktorJson)
    implementation(Dependencies.Data.ktorCore)
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appcompat)
    implementation(Dependencies.JetpackCompose.material)
    implementation(Dependencies.JetpackCompose.activityCompose)
    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.DI.koin)
}
