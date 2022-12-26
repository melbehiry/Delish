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
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}



android {
    compileSdk = libs.versions.compile.sdk.version.get().toInt()
    defaultConfig {
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.target.sdk.version.get().toInt()
        namespace = "app.delish.data"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        consumerProguardFiles("consumer-proguard-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    // Some libs (such as androidx.core:core-ktx 1.2.0 and newer) require Java 8
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
}

dependencies {
    implementation(projects.base)
    api(projects.model)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    api(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)

    api(libs.androidx.room.common)
    api(libs.androidx.paging.common)
    api(libs.timber)
    implementation(libs.androidx.core.ktx)

//    // Architecture Components
//    testImplementation(Libs.ARCH_TESTING)
//
//
    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockwebserver)

    // Retrofit
    api(libs.retrofit)
    api(libs.moshi)
    api(libs.retrofit.converter.moshi)
//    api(Libs.MOSHI_KOTLIN)

//    // Kotlin
//    implementation(Libs.KOTLIN_STDLIB)
//
    // Coroutines
    api(libs.coroutines.core)
//    testImplementation(Libs.COROUTINES_TEST)
//    implementation(Libs.COROUTINES_PLAY_SERVICE)
//
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.hamcrest)
//    testImplementation(libs.mockito.core)
//    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.javafaker)
//    testImplementation(libs.turbine)
//    testImplementation(libs.junit.ext)
    testImplementation(libs.assertj.core)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.arc.testing)
    androidTestImplementation(libs.androidx.test.runner)
//    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.assertj.core)
//    androidTestImplementation(libs.turbine)
//    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.javafaker)
//
    // unit tests livedata
    testImplementation(libs.androidx.arc.testing)

    // Data store
    api(libs.datastore)

//
//    implementation(Libs.PLAY_SERVICE_LOCATION)
}
