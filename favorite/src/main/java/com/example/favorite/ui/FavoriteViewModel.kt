package com.example.favorite.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.HyoukaUseCase

class FavoriteViewModel(hyoukaUseCase: HyoukaUseCase) : ViewModel() {
    val favorite = hyoukaUseCase.getFavoriteItems().asLiveData()
}