import com.android.build.gradle.LibraryExtension
import config.configureCompose
import config.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class LibraryFeatureComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }

            dependencies {
                add("implementation", libs.findLibrary("koin-androidx-compose").get())
                add("implementation", libs.findLibrary("compose-material").get())
            }
        }
    }
}