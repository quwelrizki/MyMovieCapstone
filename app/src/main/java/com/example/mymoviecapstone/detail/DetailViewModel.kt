package com.example.mymoviecapstone.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Hyouka
import com.example.core.domain.usecase.HyoukaUseCase

class DetailViewModel(private val hyoukaUseCase: HyoukaUseCase) : ViewModel() {
    fun setFavoriteTourism(hyouka: Hyouka, newStatus:Boolean) =
        hyoukaUseCase.setFavoriteItems(hyouka, newStatus)
}
