plugins {
    id("java-platform")
    id("maven-publish")
}

val core = "1.2.0"
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
val junitExt = "1.1.1"
val lifecycle = "2.2.0"
val material = "1.1.0"
val mockito = "3.3.1"
val mockitoKotlin = "1.5.0"
val okhttp = "3.10.0"
val room = "2.2.5"
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
val compose = "1.0.0-alpha10"
val composeNavigation = "1.0.0-alpha04"


dependencies {
    constraints {
        api("${Libs.ANDROIDX_HILT_COMPILER}:$hiltJetPack")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.COROUTINES}:$coroutines")
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
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
        api("${Libs.LIFECYCLE_LIVE_DATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.MOCKITO_CORE}:$mockito")
        api("${Libs.MOCKITO_KOTLIN}:$mockitoKotlin")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.ROOM_COMPILER}:$room")
        api("${Libs.OKHTTP}:$okhttp")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
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
        api("${Libs.LIFECYCLE_EXTENSION}:$lifecycle")
        api("${Libs.LIFECYCLE_RUN_TIME}:$lifecycle")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_UI_GRAPHICS}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$compose")
        api("${Libs.COMPOSE_FOUNDATION_LAYOUT}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_RUNTIME_LIVEDATA}:$compose")
        api("${Libs.COMPOSE_RUNTIME}:$compose")
        api("${Libs.COMPOSE_ANIMATION}:$compose")
        api("${Libs.COMPOSE_NAVIGATION}:$composeNavigation")
    }
}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}
