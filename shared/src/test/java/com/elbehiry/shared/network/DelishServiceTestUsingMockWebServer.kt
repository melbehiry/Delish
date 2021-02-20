package com.elbehiry.shared.network

import com.elbehiry.shared.data.remote.DelishApi
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.RecipesTestData
import com.elbehiry.test_shared.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class DelishServiceTestUsingMockWebServer {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val delishApiService by lazy {
        retrofit.create(DelishApi::class.java)
    }

    @Test
    fun test_get_Random_Recipes() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(RecipesTestData.recipes)
                .setResponseCode(200)
        )
        coroutineRule.runBlockingTest {
            val recipes = delishApiService.getRandomRecipes(tags = null, number = null)
            Assert.assertNotNull(recipes)
            val recipeItem = recipes.recipes[0]
            Assert.assertEquals(recipeItem?.id, RecipesTestData.id)
        }
    }

    @After
    fun tearDown() {
        try {
            mockWebServer.shutdown()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}