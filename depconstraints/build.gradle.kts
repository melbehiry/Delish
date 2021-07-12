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
    id("java-platform")
    id("maven-publish")
}

val core = "1.3.2"
val coroutines = "1.4.2"
val coroutinesTest = "1.4.2"
val drawerLayout = "1.1.0-rc01"
val espresso = "3.1.1"
val glide = "4.9.0"
val gson = "2.8.6"
val hamcrest = "1.3"
val hilt = Versions.HILT
val hiltJetPack = "1.0.0-alpha01"
val junit = "4.13"
val assertJVersion = "3.19.0"
val mockkVersion = "1.10.6"
val junitExt = "1.1.2"
val lifecycle = "2.3.1"
val lifecycleExt = "2.2.0"
val material = "1.1.0"
val mockito = "3.3.1"
val mockitoKotlin = "1.5.0"
val okhttp = "3.10.0"
val room = "2.2.6"
val rules = "1.1.1"
val runner = "1.2.0"
val threetenabp = "1.0.5"
val timber = "4.7.1"
val viewpager2 = "1.0.0"
val archTesting = "2.0.0"
val retrofit = "2.9.0"
val moshi = "1.11.0"
val kotchi = "2.3.3"
val datastore = "1.0.0-alpha05"
val compose = "1.0.0-rc01"
val composeNavigation = "2.4.0-alpha04"
val composeActivity = "1.3.0-rc01"
val composeConstraint = "1.0.0-alpha08"
val composePaging = "1.0.0-alpha11"
val composeViewModel = "1.0.0-alpha07"
val accompanist = "0.13.0"
val fragmentKtx = "1.3.2"
val appCompat = "1.2.0-rc01"
val faker = "1.0.2"
val binder = "1.0.0-alpha01"
val turbine = "0.4.1"

dependencies {
    constraints {
        api("${Libs.ANDROIDX_HILT_COMPILER}:$hiltJetPack")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.APP_COMPAT}:$appCompat")
        api("${Libs.COROUTINES}:$coroutines")
        api("${Libs.COROUTINES_ANDROID}:$coroutines")
        api("${Libs.COROUTINES_TEST}:$coroutines")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.ESPRESSO_CONTRIB}:$espresso")
        api("${Libs.GLIDE}:$glide")
        api("${Libs.GLIDE_COMPILER}:$glide")
        api("${Libs.GSON}:$gson")
        api("${Libs.HAMCREST}:$hamcrest")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_TESTING}:$hilt")
        api("${Libs.HILT_VIEWMODEL}:$hiltJetPack")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.ASSERT_J}:$assertJVersion")
        api("${Libs.MOCKK}:$mockkVersion")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
        api("${Libs.LIFECYCLE_LIVE_DATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.MOCKITO_CORE}:$mockito")
        api("${Libs.MOCKITO_KOTLIN}:$mockitoKotlin")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.FRAGMENT_KTX}:$fragmentKtx")
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.ROOM_COMPILER}:$room")
        api("${Libs.ROOM_TESTING}:$room")
        api("${Libs.OKHTTP}:$okhttp")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.OKHTTP_MOCK_SERVER}:$okhttp")
        api("${Libs.RULES}:$rules")
        api("${Libs.RUNNER}:$runner")
        api("${Libs.TIMBER}:$timber")
        api("${Libs.VIEWPAGER2}:$viewpager2")
        api("${Libs.ARCH_TESTING}:$archTesting")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.MOSHI}:$moshi")
        api("${Libs.MOSHI_RETROFIT}:$retrofit")
        api("${Libs.MOSHI_KOTLIN}:$moshi")
        api("${Libs.KOTCHI}:$kotchi")
        api("${Libs.KOTCHI_COMPILER}:$kotchi")
        api("${Libs.DATA_STORE}:$datastore")
        api("${Libs.LIFECYCLE_EXTENSION}:$lifecycleExt")
        api("${Libs.LIFECYCLE_RUN_TIME}:$lifecycle")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_UI_GRAPHICS}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.COMPOSE_FOUNDATION_LAYOUT}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_RUNTIME_LIVEDATA}:$compose")
        api("${Libs.COMPOSE_RUNTIME}:$compose")
        api("${Libs.COMPOSE_ANIMATION}:$compose")
        api("${Libs.COMPOSE_ANIMATION_CORE}:$compose")
        api("${Libs.COMPOSE_ICON}:$compose")
        api(Libs.COMPOSE_TEST)
        api("${Libs.COMPOSE_PAGING}:$composePaging")
        api("${Libs.COMPOSE_VIEW_MODEL}:$composeViewModel")
        api("${Libs.COMPOSE_ACTIVITY}:$composeActivity")
        api("${Libs.COMPOSE_CONSTRAINT}:$composeConstraint")
        api("${Libs.COMPOSE_NAVIGATION}:$composeNavigation")
        api("${Libs.INSETS}:$accompanist")
        api("${Libs.COIL}:$accompanist")
        api("${Libs.FAKER}:$faker")
        api("${Libs.HILT_BINDER}:$binder")
        api("${Libs.HILT_BINDER_COMPILER}:$binder")
        api("${Libs.TURBINE}:$turbine")
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}
