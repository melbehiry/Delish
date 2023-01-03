plugins {
    id("com.android.library")
    kotlin("android")
}

android{
    namespace = "com.elbehiry.delish.base"
}

dependencies {
    api(libs.coroutines.core)
    api(libs.dagger.dagger)
    api(libs.lifecycle.viewmodel.ktx)
}
