import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.`android-application`: PluginDependencySpec
    get() = id(Plugins.androidApplication)

val PluginDependenciesSpec.`android-library`: PluginDependencySpec
    get() = id(Plugins.androidLibrary)


val PluginDependenciesSpec.hilt: PluginDependencySpec
    get() = id(Plugins.daggerHilt)

val PluginDependenciesSpec.safeargs: PluginDependencySpec
    get() = id(Plugins.safeargs)
