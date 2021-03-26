// Top-level build file where you can add configuration options common to all
// sub-projects/modules.
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

plugins {
    id("com.diffplug.spotless") version "5.10.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()

//        flatDir {
//            dirs = setOf(file("libs"))
//        }

        if (Versions.snapshot.isNotEmpty()) {
            maven("https://androidx.dev/snapshots/builds/${Versions.snapshot}/artifacts/repository/")
        }

        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    val ktlintVer = "0.40.0"
    spotless {
        kotlin {
            target("**/*.kt")
            ktlint(ktlintVer).userData(
                mapOf("max_line_length" to "100", "disabled_rules" to "import-ordering")
            )
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
        kotlinGradle {
            // same as kotlin, but for .gradle.kts files (defaults to '*.gradle.kts')
            target("**/*.gradle.kts")
            ktlint(ktlintVer)
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(plugins |import |include)")
        }
    }

    // `spotlessCheck` runs when a build includes `check`, notably during presubmit. In these cases
    // we prefer `spotlessCheck` run as early as possible since it fails in seconds. This prevents a
    // build from running for several minutes doing other intensive tasks (resource processing, code
    // generation, compilation, etc) only to fail on a formatting nit.
    // Using `mustRunAfter` avoids creating a task dependency. The order is enforced only if
    // `spotlessCheck` is already scheduled to run, so we can still build and launch from the IDE
    // while the code is "dirty".
    tasks.whenTaskAdded {
        if (name == "preBuild") {
            mustRunAfter("spotlessCheck")
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
                    "kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview," +
                    "kotlin.time.ExperimentalTime" +
                    "-Xallow-jvm-ir-dependencies"
    }

    tasks.withType(KotlinCompile::class.java).all {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xuse-experimental=kotlin.ExperimentalStdlibApi"
            )
        }
    }

}
