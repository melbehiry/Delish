pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = ("Delish")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":mobile",
    ":shared",
    ":test-shared",
    ":model"
)
