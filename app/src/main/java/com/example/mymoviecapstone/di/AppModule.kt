package com.example.mymoviecapstone.di

import com.example.core.domain.usecase.HyoukaInteractor
import com.example.core.domain.usecase.HyoukaUseCase
import com.example.mymoviecapstone.detail.DetailViewModel
import com.example.mymoviecapstone.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<HyoukaUseCase> { HyoukaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}