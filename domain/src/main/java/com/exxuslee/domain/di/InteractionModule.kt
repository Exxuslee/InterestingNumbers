package com.exxuslee.domain.di

import com.exxuslee.domain.usecases.NumberUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory<NumberUseCase.Base> { NumberUseCase.Base(get()) }
}