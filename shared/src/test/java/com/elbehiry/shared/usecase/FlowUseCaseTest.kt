package com.elbehiry.shared.usecase

import com.elbehiry.shared.MainCoroutineRule
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.runBlockingTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlowUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var useCase: ExceptionUseCase

    @Before
    fun setup() {
        testDispatcher = coroutineRule.testDispatcher
        useCase = ExceptionUseCase(testDispatcher)
    }


    @Test
    fun `exception emits Result#Error`() {
        coroutineRule.runBlockingTest {
            val result = useCase(Unit)
            assert(result.first() is Result.Error)
        }
    }

    inner class ExceptionUseCase(dispatcher: CoroutineDispatcher) :
        FlowUseCase<Unit, Unit>(dispatcher) {
        override fun execute(parameters: Unit): Flow<Result<Unit>> = flow {
            throw Exception("Test exception")
        }

    }

}