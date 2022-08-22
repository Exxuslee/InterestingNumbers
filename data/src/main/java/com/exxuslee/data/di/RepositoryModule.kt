package com.exxuslee.data.di

import com.exxuslee.data.repositories.NumberRepositoryImpl
import com.exxuslee.domain.repositories.NumberRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory<NumberRepository> { NumberRepositoryImpl(get(), get()) }
}