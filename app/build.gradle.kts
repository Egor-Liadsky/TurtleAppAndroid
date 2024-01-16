plugins {
    id("turtleapp.application")
    id("turtleapp.application.compose")
}

android {
    namespace = "com.turtleteam.turtleapp"

    defaultConfig {
        applicationId = "com.turtleteam.turtleapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core_navigation"))
    implementation(project(":core_view"))
    implementation(project(":core_data"))
    implementation(project(":storage"))

    implementation(project(":feature:welcome:api"))
    implementation(project(":feature:welcome:impl"))

    implementation(project(":feature:group:api"))
    implementation(project(":feature:group:impl"))

    implementation(project(":feature:teacher:api"))
    implementation(project(":feature:teacher:impl"))

    implementation(project(":feature:additional:api"))
    implementation(project(":feature:additional:impl"))

    implementation(project(":feature:settings:api"))
    implementation(project(":feature:settings:impl"))

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.koin.androidx.compose)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.compose.material)
}

