plugins {
    id("turtleapp.library")
}

android {
    namespace = "com.example.impl"

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
    implementation(project(":core_data:api"))
    implementation(project(":storage"))

    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.androidx.compose)
}