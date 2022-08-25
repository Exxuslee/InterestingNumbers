package com.exxuslee.data.repositories

import com.exxuslee.data.local.dao.NumberDAO
import com.exxuslee.data.local.entities.NumberEntity
import com.exxuslee.data.remote.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class NumberRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var cardInfoDao: NumberDAO

    private lateinit var systemUnderTest: NumberRepositoryImpl
    private val fakeNumberEntity = NumberEntity(number = 1, content = "1 is one")

    @Before
    fun setUp() {
        systemUnderTest = NumberRepositoryImpl(apiService, cardInfoDao)
    }

    @Test
    fun getCardInfo_fromRemote_returnSuccessCardInfo() = runBlocking {
        `when`(apiService.getNumber(1)).thenReturn(Response.success("1 is one"))

        systemUnderTest.getNumber(1)

        verify(apiService, times(1)).getNumber(1)
        verify(cardInfoDao, times(1)).insertNumber(fakeNumberEntity)
    }
}