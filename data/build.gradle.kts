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
        namespace = "com.delish.shared"

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

    // debug and release variants share the same source dir
    sourceSets {
        getByName("debug") {
            java.srcDir("src/debugRelease/java")
        }
        getByName("release") {
            java.srcDir("src/debugRelease/java")
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

    buildTypes {
        val urlName = "SPOONACULAR_BASE_URL"
        val baseUrl = "\"https://api.spoonacular.com/\""
        val keyName = "SPOONACULAR_KEY"
        val keyValue = "\"2d1acf7218d245b9b88d52a1b8362569\""
//        val keyValue = "\"34410d759eb443e5a0b6fa9f01af0f94\""
        val cuisinesName = "CUISINES_DATA_URL"
        val cuisinesValue =
            "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/getCuisines.json?alt=media&token=20daa785-e0e4-4ef5-97f8-b8c62f106900\""
        val ingredientsName = "INGREDIENTS_DATA_URL"
        val fourSquareSearchUrl = "FOURSQUARE_Search_Url"
        val fourSquareClientKeyName = "FOURSQUARE_CLIENT_ID"
        val fourSquareSecretKeyName = "FOURSQUARE_SECRET_ID"
        val ingredientsValue =
            "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/ingredients.json?alt=media&token=9361ddbe-b7e9-4d18-b9a9-530f222e4890\""
        val fourSquareClientKeyValue = "\"TGXNNR0CV15HF05YCZPMYDJWEEQZHRDPGWYYCRJWXF0LJNRB\""
        val fourSquareSecretKeyValue = "\"MZ5Q1A0HPCBFI3FZLTBCMQIWU3R1GYNPTZNBMI4JTB23ZRKA\""
        val fourSquareSearchUrlValue = "\"https://api.foursquare.com/v2/venues/search\""
        getByName("release") {
            buildConfigField("String", urlName, baseUrl)
            buildConfigField("String", keyName, keyValue)
            buildConfigField("String", cuisinesName, cuisinesValue)
            buildConfigField("String", ingredientsName, ingredientsValue)
            buildConfigField("String", fourSquareSearchUrl, fourSquareSearchUrlValue)
            buildConfigField("String", fourSquareClientKeyName, fourSquareClientKeyValue)
            buildConfigField("String", fourSquareSecretKeyName, fourSquareSecretKeyValue)
        }
        getByName("debug") {
            buildConfigField("String", urlName, baseUrl)
            buildConfigField("String", keyName, keyValue)
            buildConfigField("String", cuisinesName, cuisinesValue)
            buildConfigField("String", ingredientsName, ingredientsValue)
            buildConfigField("String", fourSquareSearchUrl, fourSquareSearchUrlValue)
            buildConfigField("String", fourSquareClientKeyName, fourSquareClientKeyValue)
            buildConfigField("String", fourSquareSecretKeyName, fourSquareSecretKeyValue)
        }
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
    implementation(projects.testShared)
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
