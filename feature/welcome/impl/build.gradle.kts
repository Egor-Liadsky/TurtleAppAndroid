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
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {

    implementation(project(":feature:welcome:api"))
    implementation(project(":feature:group:api"))
    implementation(project(":feature:teacher:api"))
    implementation(project(":core_view"))
    implementation(project(":core_navigation"))
    implementation(project(":core_data:api"))
    implementation(project(":storage"))

    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.compose.material)
}
