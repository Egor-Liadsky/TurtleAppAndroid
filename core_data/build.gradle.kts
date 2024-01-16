plugins {
    id("turtleapp.library")
}

android {
    namespace = "com.turtleteam.core_network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":storage"))
    implementation(project(":core_view")) //TODO core_data НЕ ДОЛЖНА ИМЕТЬ ДОСТУП К core_view!!!!

    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.androidx.compose)
}
