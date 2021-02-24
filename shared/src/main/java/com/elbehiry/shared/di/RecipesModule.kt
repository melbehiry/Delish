package com.elbehiry.shared.di

import com.elbehiry.shared.data.recipes.cuisines.remote.GetCuisinesDataSource
import com.elbehiry.shared.data.recipes.cuisines.remote.GetCuisinesRemoteDataSource
import com.elbehiry.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.elbehiry.shared.data.recipes.cuisines.repository.GetCuisinesRepository
import com.elbehiry.shared.data.recipes.random.remote.GetRandomRecipesRemoteDataSource
import com.elbehiry.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource
import com.elbehiry.shared.data.recipes.random.repository.GetRandomRecipesRepository
import com.elbehiry.shared.data.recipes.random.repository.RandomRecipesRepository
import com.elbehiry.shared.data.remote.DelishApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RecipesModule {

    @Provides
    fun provideRandomRecipesDataSource(api: DelishApi): RandomRecipesRemoteDataSource =
        GetRandomRecipesRemoteDataSource(api)

    @Provides
    fun provideRandomRecipesRepository(
        randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
    ): RandomRecipesRepository =
        GetRandomRecipesRepository(randomRecipesRemoteDataSource)

    @Provides
    fun provideGetCuisinesDataSource(api: DelishApi): GetCuisinesDataSource =
        GetCuisinesRemoteDataSource(api)

    @Provides
    fun provideCuisinesRepository(
        getCuisinesDataSource: GetCuisinesDataSource
    ): CuisinesRepository =
        GetCuisinesRepository(getCuisinesDataSource)
}