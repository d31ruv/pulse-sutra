import com.android.build.api.dsl.CommonExtension
import d31ruv.diecast.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

abstract class KotlinComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            pluginManager.withPlugin("com.android.application") {
                val extension = extensions.getByType<CommonExtension>()
                configureAndroidCompose(extension)
            }

            pluginManager.withPlugin("com.android.library") {
                val extension = extensions.getByType<CommonExtension>()
                configureAndroidCompose(extension)
            }
        }
    }
}