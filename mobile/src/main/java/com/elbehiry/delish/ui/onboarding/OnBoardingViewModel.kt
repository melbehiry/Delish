package com.elbehiry.delish.ui.onboarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.shared.domain.pref.OnBoardingCompleteActionUseCase
import kotlinx.coroutines.launch

class OnBoardingViewModel @ViewModelInject constructor(
    val onBoardingCompleteActionUseCase: OnBoardingCompleteActionUseCase
) : ViewModel() {

    private val _navigateToMainActivity = MutableLiveData<Boolean>()
    val navigateToMainActivity: LiveData<Boolean> = _navigateToMainActivity


    fun getStartedClick() {
        viewModelScope.launch {
            onBoardingCompleteActionUseCase(true)
            _navigateToMainActivity.postValue(true)
        }
    }
}