import com.android.build.gradle.LibraryExtension
import config.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryFeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("turtleapp.library")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }
        }
    }
}