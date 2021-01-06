package com.elbehiry.shared.data.pref.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun save(key: Preferences.Key<Boolean>)
    fun read(key: Preferences.Key<Boolean>): Flow<Boolean>
}