plugins {
    id("turtleapp.library.feature")
    id("turtleapp.library.feature.compose")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.core_view"

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
    implementation(libs.ktor.serialization) //TODO надо убрать модели
    implementation(libs.compose.material)
//    implementation(Dependencies.Android.lifecycle)
}
