pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TurtleSchedule"
include(":app")
include(":feature")
include(":core_navigation")
include(":core_view")
include(":storage")

// Data
//include(":core_data")
include(":core_data:api")
include(":core_data:impl")

// Welcome
include(":feature:welcome")
include(":feature:welcome:api")
include(":feature:welcome:impl")

// Group
include(":feature:group")
include(":feature:group:api")
include(":feature:group:impl")

// Teacher
include(":feature:teacher")
include(":feature:teacher:api")
include(":feature:teacher:impl")

// Additional
include(":feature:additional")
include(":feature:additional:api")
include(":feature:additional:impl")

// Settings
include(":feature:settings")
include(":feature:settings:api")
include(":feature:settings:impl")
include(":core_data:api")
include(":core_data:impl")
include(":analytics")
