package com.exxuslee.interestingnumbers.di

import com.exxuslee.interestingnumbers.ui.first.FirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { FirstViewModel(get()) }
}