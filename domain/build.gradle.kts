plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "app.delish.domain"
}

dependencies {
    implementation(projects.base)
    api(projects.data)
    api(libs.androidx.paging.common)
    implementation(libs.androidx.paging.runtime)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)
}
