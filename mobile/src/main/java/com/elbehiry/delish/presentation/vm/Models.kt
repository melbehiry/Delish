package com.elbehiry.delish.presentation.vm

import app.delish.base.vm.MviViewModel.MviEvent
import app.delish.base.vm.MviViewModel.MviSideEffect
import app.delish.base.vm.MviViewModel.MviViewState
import app.delish.base.vm.MviViewModel.MviViewResult

internal sealed interface ViewEvent : MviEvent {
    object OnBoardingStatus : ViewEvent
}

internal interface ViewEffect : MviSideEffect

internal data class ViewState(
    val isOnBoardingShown : Boolean? = null
) : MviViewState


internal sealed interface ViewResult : MviViewResult {
    data class OnBoardingShown(val isShown: Boolean) : ViewResult
}
