package com.elbehiry.shared.data.pref

import androidx.datastore.preferences.core.preferencesKey

object PreferencesKeys {
    val onBoardingCompletedKey = preferencesKey<Boolean>("show_completed")
}
