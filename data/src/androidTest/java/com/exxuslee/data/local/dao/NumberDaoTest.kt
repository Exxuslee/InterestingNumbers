package com.exxuslee.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.exxuslee.data.local.NumberDatabase
import com.exxuslee.data.local.entities.NumberEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class NumberDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NumberDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NumberDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert_returnSame() = runBlocking {
        val cardInfoEntity = NumberEntity(id = 1, number = 1, content = "1 number one")
        database.numberDAO.insertNumber(cardInfoEntity)
        val returned = database.numberDAO.getNumber(1)

        assertThat(returned as NumberEntity, notNullValue())
        assertThat(returned.id, `is`(cardInfoEntity.id))
        assertThat(returned.number, `is`(cardInfoEntity.number))
        assertThat(returned.content, `is`(cardInfoEntity.content))
    }
}