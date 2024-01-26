plugins {
    id("turtleapp.library")
}

android {
    namespace = "com.example.impl"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String","planshetkaUrl", "\"https://drive.google.com/drive/folders/19yyXXullGGMIT3XISiZ33wkDxHJy0zvb?usp=sharing\"")
            buildConfigField("Integer","versionCode", libs.versions.versionCode.get())
            buildConfigField("String", "versionName", "\"${libs.versions.versionName.get()}\"")
            buildConfigField("String", "baseUrl", "\"http//45.155.207.232\"")
            buildConfigField("String", "feedbackEmail", "\"liadsky.egor@gmail.com\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String","planshetkaUrl", "\"https://drive.google.com/drive/folders/19yyXXullGGMIT3XISiZ33wkDxHJy0zvb?usp=sharing\"")
            buildConfigField("Integer","versionCode", libs.versions.versionCode.get())
            buildConfigField("String", "versionName", "\"${libs.versions.versionName.get()}\"")
            buildConfigField("String", "baseUrl", "\"http//45.155.207.232\"")
            buildConfigField("String", "feedbackEmail", "\"liadsky.egor@gmail.com\"")
        }
    }
}

dependencies {
    implementation(project(":core_data:api"))
    implementation(project(":storage"))

    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.androidx.compose)
}