package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.NumberRepository
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NumberUseCaseTest {

    @Mock
    private lateinit var numberRepository: NumberRepository
    private lateinit var numberUseCase: NumberUseCase.Base

    @Before
    fun setUp() {
        numberUseCase = NumberUseCase.Base(numberRepository)
    }

    @Test
    fun useCase_calls_NumberRepository() {
        runTest {
            numberUseCase.getRandom()
            Mockito.verify(numberRepository).getRandom()
        }
    }

    @Test
    fun `return data from repository`() {

        runTest {
            val expected = Result.Success(Pair(7, "7 is the number of days in a week."))
            Mockito.`when`(numberRepository.getNumber(7)).thenReturn(expected)
            val actual = numberUseCase.getNumber(7)

            Assertions.assertEquals(expected, actual)
        }
    }

    @AfterEach
    fun tearDown(){
        Mockito.reset(numberRepository)
        Mockito.reset(numberUseCase)
    }
}