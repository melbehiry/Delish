/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `android-application`
    kotlin("android")
    kotlin("kapt")
    hilt
//    safeargs
}


android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()
    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        namespace = "com.elbehiry.delish"

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
                arguments["room.incremental"] = "true"
            }
        }
        manifestPlaceholders["googleMapsKey"] = "AIzaSyAlPDIoP7vmHfGJwQrTjA8-29OToUIESBA"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }

    // debug and release variants share the same source dir
    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
        }
    }

    // Required for AR because it includes a library built with Java 8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compilerextension.get()
    }

    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.testShared)
    api(projects.model)
    implementation(libs.hilt.android)
    implementation(libs.hilt.viewmodel)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    kapt(libs.lifecycle.compiler)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.hilt.android)
    implementation(libs.hilt.viewmodel)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)

    // COMPOSE
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.grapics)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.livedata)
    implementation(libs.compose.animation)
    implementation(libs.compose.navigation)
    implementation(libs.compose.icons.extended)
    implementation(libs.compose.activity)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.paging)
    implementation(libs.compose.viewmodel)

    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.coil)
    implementation(libs.accompanist.permissions)

//    testImplementation(Libs.ARCH_TESTING)
//    kaptAndroidTest(Libs.HILT_COMPILER)
//    kaptAndroidTest(Libs.ANDROIDX_HILT_COMPILER)
//
//    // Kotlin
//    implementation(Libs.KOTLIN_STDLIB)
//
//    // Local unit tests
//    testImplementation(Libs.JUNIT)
//    testImplementation(Libs.EXT_JUNIT)
//    testImplementation(Libs.ASSERT_J)
//    testImplementation(Libs.MOCKK)
//    testImplementation(Libs.FAKER)
//
//    // unit tests livedata
//    testImplementation(Libs.ARCH_TESTING)
//
//    // flow
//    testImplementation(Libs.TURBINE)
//
//
//    androidTestImplementation(Libs.COMPOSE_TEST)
//    // play service
//    implementation(Libs.COROUTINES_PLAY_SERVICE)
//    implementation(Libs.PLAY_SERVICE_LOCATION)
//
//    // Maps
//    api(Libs.GOOGLE_MAP_UTILS_KTX) {
//        exclude(group = "com.google.android.gms")
//    }
//    api(Libs.GOOGLE_PLAY_SERVICES_MAPS_KTX)
}
//apply(plugin = "com.google.gms.google-services")
