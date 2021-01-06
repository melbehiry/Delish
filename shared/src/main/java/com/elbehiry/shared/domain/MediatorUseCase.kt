package com.elbehiry.shared.domain

import androidx.lifecycle.MediatorLiveData

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [com.elbehiry.shared.result.Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    open fun observe(): MediatorLiveData<Result<R>> {
        return result
    }

    abstract fun execute(parameters: P)
}