package com.delish.base.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delish.base.vm.MviViewModel.MviEvent
import com.delish.base.vm.MviViewModel.MviViewResult
import com.delish.base.vm.MviViewModel.MviViewState
import com.delish.base.vm.MviViewModel.MviSideEffect
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

abstract class MviViewModel<Event : MviEvent, Result : MviViewResult, State : MviViewState, Effect : MviSideEffect>(
    initialState: State
) : ViewModel() {
    val states: StateFlow<State>
    val effects: Flow<Effect>
    private val events = MutableSharedFlow<Event>()

    init {
        events
            .share()
            .toResults()
            .share()
            .also { results ->
                states = results.toStates(initialState)
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.Lazily,
                        initialValue = initialState
                    )
                effects = results.toEffects()
            }
    }

    fun processEvent(event: Event) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    protected abstract fun Flow<Event>.toResults(): Flow<Result>
    protected abstract fun Result.reduce(state: State): State
    protected open fun Flow<Result>.toEffects(): Flow<Effect> = emptyFlow()

    private fun Flow<Result>.toStates(initialState: State): Flow<State> {
        return scan(initialState) { state, result -> result.reduce(state) }
    }

    private fun <T> Flow<T>.share(): Flow<T> {
        return shareIn(scope = viewModelScope, started = SharingStarted.Eagerly)
    }

    interface MviViewState
    interface MviSideEffect
    interface MviEvent
    interface MviViewResult
}