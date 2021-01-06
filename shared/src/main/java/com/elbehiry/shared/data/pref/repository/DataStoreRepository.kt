package com.elbehiry.shared.data.pref.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {

    override suspend fun save(key: Preferences.Key<Boolean>) {
        dataStore.edit {
            val value = it[key] ?: false
            it[key] = !value
        }
    }

    override fun read(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return dataStore.data
            .catch {
                when (it) {
                    is IOException -> {
                        it.printStackTrace()
                        emit(emptyPreferences())
                    }
                    else -> throw  it
                }
            }.map {
                it[key] ?: false
            }
    }
}