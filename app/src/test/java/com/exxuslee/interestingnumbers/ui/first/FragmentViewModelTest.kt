package com.exxuslee.interestingnumbers.ui.first

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exxuslee.domain.usecases.NumberUseCase
import com.exxuslee.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FragmentViewModelTest {

    @Mock
    private lateinit var numberUseCase: NumberUseCase.Base

    private lateinit var systemUnderTest: FirstViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        systemUnderTest = FirstViewModel(numberUseCase)

    }

    @Test
    fun numberRemote_errorReturned_dataFetchState() = runBlocking {
        `when`(numberUseCase.getNumber(1)).thenReturn(Result.Error("Invalid"))
        systemUnderTest.getNumber(1)
        MatcherAssert.assertThat(systemUnderTest.dataFetchState.getOrAwaitValue(), equalTo(false))
    }

    @Test
    fun numberRemote_successDataReturned_dataFetchState() = runBlocking {
        `when`(numberUseCase.getNumber(1)).thenReturn(Result.Success(Pair(1, "1 is one")))
        systemUnderTest.getNumber(1)
        MatcherAssert.assertThat(systemUnderTest.dataFetchState.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun numberRemote_successCardInfoReturned_checkCardInfoValue() = runBlocking {
        `when`(numberUseCase.getNumber(1)).thenReturn(Result.Success(Pair(1, "1 is one")))
        systemUnderTest.getNumber(1)
        MatcherAssert.assertThat(systemUnderTest.ids.getOrAwaitValue(),
            `is`(mapOf(Pair(1, "1 is one"))))
    }
}