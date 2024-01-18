plugins {
    id("turtleapp.library.feature")
    id("turtleapp.library.feature.compose")
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
    implementation(project(":core_data:api"))
    implementation(libs.compose.material)
}
