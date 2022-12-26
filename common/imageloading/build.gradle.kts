plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "app.delish.common.imageloading"
}
dependencies {
    implementation(projects.base)
    implementation(projects.common.view)

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    api(libs.coil.coil)
}