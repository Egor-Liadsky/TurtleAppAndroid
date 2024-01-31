plugins {
    id("turtleapp.application")
    id("turtleapp.application.compose")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.turtleteam.turtleapp"

    defaultConfig {
        applicationId = "com.turtleteam.turtleapp"

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
    implementation(project(":core_data:api"))
    implementation(project(":core_data:impl"))
    implementation(project(":storage"))
    implementation(project(":analytics"))

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

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.koin.androidx.compose)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.compose.material)
}

