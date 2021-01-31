package com.elbehiry.delish.ui.launcher

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.collect

class LauncherViewModel @ViewModelInject constructor(
    val onBoardingCompletedUseCase: OnBoardingCompletedUseCase
) : ViewModel() {
    val launchDestination: LiveData<Boolean> = liveData {
        onBoardingCompletedUseCase(Unit).collect { result ->
            if (result is Result.Success) {
                emit(result.data)
            } else {
                emit(false)
            }
        }
    }
}