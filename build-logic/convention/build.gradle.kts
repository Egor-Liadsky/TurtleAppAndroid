import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "turtleapp.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("androidCompose") {
            id = "turtleapp.application.compose"
            implementationClass = "ApplicationComposeConventionPlugin"
        }
        register("library") {
            id = "turtleapp.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("libraryFeature") {
            id = "turtleapp.library.feature"
            implementationClass = "LibraryFeatureConventionPlugin"
        }
        register("libraryFeatureCompose") {
            id = "turtleapp.library.feature.compose"
            implementationClass = "LibraryFeatureComposeConventionPlugin"
        }
    }
}