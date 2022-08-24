package com.exxuslee.interestingnumbers.ui.first

import com.exxuslee.domain.repositories.NumberRepository
import com.exxuslee.domain.usecases.NumberUseCase
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verifyNoMoreInteractions

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FirstViewModelTest {

    @Mock
    private lateinit var numberRepository: NumberRepository
    private lateinit var getNumber: NumberUseCase.Base
    private lateinit var viewModel: FirstViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        getNumber = NumberUseCase.Base(numberRepository)
        viewModel = FirstViewModel(getNumber)
        Dispatchers.setMain(dispatcher)
    }

    @AfterEach
    fun tearDown() {
        Mockito.reset(getNumber)
        Mockito.reset(numberRepository)
        Dispatchers.resetMain()
    }

    @Test
    fun `should getRandomNumber`() {

    }

    @Test
    fun `should getNumber`() {
        runTest {
            val resp = Result.Success(Pair(7, "7 is the number of days in a week."))
            Mockito.`when`(numberRepository.getNumber(7)).thenReturn(resp)
            viewModel.getNumber(7)
            val actual = Pair(7, "7 is the number of days in a week.")
            val expected = viewModel.ids.value
            Assertions.assertEquals(expected, actual)
        }

    }

    @Test
    fun `should navigate`() {

    }

    @Test
    fun `should removeNumber`() {

    }
}