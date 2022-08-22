package com.exxuslee.data.di

import androidx.room.Room
import com.exxuslee.data.local.NumberDatabase
import com.exxuslee.data.utils.Constants.DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), NumberDatabase::class.java, DB)
            .fallbackToDestructiveMigration().build()
    }

    factory { get<NumberDatabase>().numberDAO }
}