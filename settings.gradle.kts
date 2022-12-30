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
    ":data",
    ":model",
    ":base",
    ":domain",
    ":data",
    ":common:imageloading",
    ":common:compose",
    ":common:resources",
    ":ui:onboarding",
    ":ui:discover",
    ":ui:search",
    ":ui:bookmark",
    ":ui:details",
    ":ui:settings"
)
