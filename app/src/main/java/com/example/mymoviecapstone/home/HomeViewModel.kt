package com.example.mymoviecapstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.HyoukaUseCase

class HomeViewModel(hyoukaUseCase: HyoukaUseCase) : ViewModel() {
    val hyouka = hyoukaUseCase.getAllItems().asLiveData()
}