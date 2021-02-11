package com.elbehiry.delish.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elbehiry.delish.ui.onboarding.OnBoardingViewModel
import com.elbehiry.shared.data.pref.repository.DataStoreOperations
import com.elbehiry.shared.domain.pref.OnBoardingCompleteActionUseCase
import com.elbehiry.test_shared.MainCoroutineRule
import come.elbehiry.app.delish.androidtest.util.LiveDataTestUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OnBoardingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: DataStoreOperations

    private lateinit var onBoardingCompleteActionUseCase: OnBoardingCompleteActionUseCase
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Before
    fun setUp() {
        onBoardingCompleteActionUseCase = OnBoardingCompleteActionUseCase(
            repository, coroutineRule.testDispatcher
        )
        onBoardingViewModel = OnBoardingViewModel(onBoardingCompleteActionUseCase)
    }

    @Test
    fun `get started clicked should update the pref`() {
        onBoardingViewModel.getStartedClick()
        val navigateValue = LiveDataTestUtil.getValue(onBoardingViewModel.navigateToMainActivity)
        Assert.assertNotNull(navigateValue)
    }
}