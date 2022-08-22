package com.exxuslee.domain.usecases

import com.exxuslee.domain.repositories.NumberRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PriceUseCaseTest {

    @Mock
    private lateinit var idRepository: NumberRepository
    private lateinit var getNumber: GetNumberUseCase.Base

    @Before
    fun setUp() {
        getNumber = GetNumberUseCase.Base(idRepository)
    }

    @Test
    fun getIDUseCase_calls_IDRepository() {
        runTest {
            getNumber.getRandom()
            Mockito.verify(idRepository).getRandom()
        }
    }
}