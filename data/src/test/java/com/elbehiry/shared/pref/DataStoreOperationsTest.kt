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

package com.elbehiry.shared.pref
//
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import com.elbehiry.test_shared.MainCoroutineRule
//import com.elbehiry.shared.data.pref.PreferencesKeys
//import com.elbehiry.shared.data.pref.repository.DataStoreOperations
//import com.elbehiry.shared.data.pref.repository.DataStoreRepository
//import com.elbehiry.test_shared.runBlockingTest
//import kotlinx.coroutines.flow.collect
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//import com.elbehiry.shared.result.Result
//
//@RunWith(MockitoJUnitRunner::class)
//class DataStoreOperationsTest {
//
//    @get:Rule
//    val coroutineTestRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var preferences: DataStore<Preferences>
//    private lateinit var dataStoreRepository: DataStoreOperations
//    private val operationKey by lazy {
//        PreferencesKeys.onBoardingCompletedKey
//    }
//
//    @Before
//    fun setup() {
//        dataStoreRepository = DataStoreRepository(preferences)
//    }
//
//    @Test
//    fun `should return default value if key not exist`() = coroutineTestRule.runBlockingTest {
//        val defaultValue = true
//        dataStoreRepository.read(operationKey).collect { result ->
//            Assert.assertEquals(result, Result.Success(defaultValue))
//        }
//    }
//
//    @Test
//    fun `read value should return the previous saved value`() = coroutineTestRule.runBlockingTest {
//        val savedValue = true
//        dataStoreRepository.save(operationKey, savedValue)
//        dataStoreRepository.read(operationKey).collect { result ->
//            Assert.assertEquals(result, Result.Success(savedValue))
//        }
//    }
//}
