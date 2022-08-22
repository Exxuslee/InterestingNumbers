package com.exxuslee.domain.di

import com.exxuslee.domain.usecases.GetNumberUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory<GetNumberUseCase.Base> { GetNumberUseCase.Base(get()) }
}