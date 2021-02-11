package com.elbehiry.shared.data.pref.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreOperations {

    override suspend fun save(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit {
            it[key] = value
        }
    }

    override fun read(key: Preferences.Key<Boolean>): Flow<Result<Boolean>> {
        return dataStore.data
            .map {
                Result.Success(it[key] ?: false)
            }.catch {
                Result.Error(Exception(it))
            }
    }
}