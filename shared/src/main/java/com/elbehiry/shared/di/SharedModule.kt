package com.elbehiry.shared.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.elbehiry.shared.data.pref.repository.DataStoreOperations
import com.elbehiry.shared.data.pref.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

const val dataStoreName = "DelishDataStore"

@InstallIn(ApplicationComponent::class)
@Module
class SharedModule {

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ) = context.createDataStore(dataStoreName)

    @Singleton
    @Provides
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreOperations =
        DataStoreRepository(dataStore)
}