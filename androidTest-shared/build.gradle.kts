
plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)
    defaultConfig {
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lintOptions {
        // Version changes are beyond our control, so don't warn. The IDE will still mark these.
        disable("GradleDependency")
    }
}

dependencies {
    api(platform(project(":depconstraints")))

    implementation(Libs.KOTLIN_STDLIB)

    // Architecture Components
    implementation(Libs.LIFECYCLE_LIVE_DATA_KTX)
    implementation(Libs.LIFECYCLE_VIEW_MODEL_KTX)
}
