package com.elbehiry.delish.ui.launcher

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.collect
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.MAIN_ACTIVITY
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.ONBOARDING

class LauncherViewModel @ViewModelInject constructor(
    val onBoardingCompletedUseCase: OnBoardingCompletedUseCase
) : ViewModel() {
    val launchDestination: LiveData<LaunchDestination> = liveData {
        onBoardingCompletedUseCase(Unit).collect { result ->
            if (result is Result.Success) {
                if (result.data) {
                    emit(MAIN_ACTIVITY)
                } else {
                    emit(ONBOARDING)
                }
            } else {
                emit(ONBOARDING)
            }
        }
    }

    enum class LaunchDestination {
        ONBOARDING,
        MAIN_ACTIVITY
    }

}