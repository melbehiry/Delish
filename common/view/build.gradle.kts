plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "app.delish.common.ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(platform(libs.compose.bom))
    api(projects.common.resources)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.compose.material.material)
    implementation(libs.compose.constraint.layout)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material.iconsext)
    implementation(libs.compose.ui.tooling)
}
