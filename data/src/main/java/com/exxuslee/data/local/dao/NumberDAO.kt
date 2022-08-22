package com.exxuslee.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exxuslee.data.local.entities.NumberEntity

@Dao
interface NumberDAO {

    @Query("UPDATE table_number SET content = :content WHERE number = :number")
    fun setNumber(number: Int, content: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(numberEntity: NumberEntity)

    @Query("SELECT * FROM table_number WHERE number = :number")
    fun number(number: Int): NumberEntity?


}