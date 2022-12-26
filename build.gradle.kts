import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.versions)
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.hilt) apply false
    base
}

val ktlintVersion = libs.versions.ktlint.asProvider().get()
val detektFormatting = libs.detekt.formatting

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
    }

    dependencies {
        detektPlugins(detektFormatting)
    }
}
subprojects {

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors = false

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.Experimental",
                "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
                "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
                "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            )

            if (project.hasProperty("tivi.enableComposeCompilerReports")) {
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics"
                )
                freeCompilerArgs += listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                            project.buildDir.absolutePath + "/compose_metrics"
                )
            }

            // Set JVM target to 11
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    plugins.withId(rootProject.libs.plugins.hilt.get().pluginId) {
        extensions.getByType<dagger.hilt.android.plugin.HiltExtension>().enableAggregatingTask = true
    }
    plugins.withId(rootProject.libs.plugins.kotlin.kapt.get().pluginId) {
        extensions.getByType<org.jetbrains.kotlin.gradle.plugin.KaptExtension>().correctErrorTypes = true
    }
    plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
        extensions.configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(libs.versions.compile.sdk.version.get().toInt())
            defaultConfig {
                minSdk = libs.versions.min.sdk.version.get().toInt()
                targetSdk = libs.versions.target.sdk.version.get().toInt()
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}
