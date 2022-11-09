plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "app.delish.common.ui"
}

dependencies {
    implementation(projects.shared)
    api(projects.common.resources)

    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.coroutines.core)

    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.emoji)

    implementation(libs.hilt.library)
}
