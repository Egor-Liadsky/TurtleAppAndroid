plugins {
    id("turtleapp.library.feature")
    id("turtleapp.library.feature.compose")
}

android {
    namespace = "com.turtleteam.impl"

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

    implementation(project(":feature:settings:api"))
    implementation(project(":core_view"))
    implementation(project(":core_data:api"))
    implementation(project(":core_navigation"))
    implementation(project(":storage"))

    implementation(libs.compose.material)
    implementation(libs.ktor.serialization)
}
