package com.example.core.domain.usecase

import com.example.core.domain.model.Hyouka
import com.example.core.domain.respository.IHyoukaRepository

class HyoukaInteractor(private val hyoukaRepository: IHyoukaRepository): HyoukaUseCase {
    override fun getAllItems() = hyoukaRepository.getAllItems()
    override fun getFavoriteItems()= hyoukaRepository.getFavoriteItems()
    override fun setFavoriteItems(items: Hyouka, state: Boolean) = hyoukaRepository.setFavoriteItems(items,state)
}