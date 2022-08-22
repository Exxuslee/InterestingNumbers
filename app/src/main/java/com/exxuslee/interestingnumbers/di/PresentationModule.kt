package com.exxuslee.interestingnumbers.di

import com.exxuslee.interestingnumbers.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
}