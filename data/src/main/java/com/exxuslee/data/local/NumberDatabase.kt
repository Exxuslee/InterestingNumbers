package com.exxuslee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exxuslee.data.local.dao.NumberDAO
import com.exxuslee.data.local.entities.NumberEntity


@Database(entities = [NumberEntity::class], version = 1, exportSchema = false)
abstract class NumberDatabase : RoomDatabase() {
    abstract val numberDAO: NumberDAO
}