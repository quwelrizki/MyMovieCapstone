package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Hyouka
import kotlinx.coroutines.flow.Flow

interface HyoukaUseCase {
    fun getAllItems(): Flow<Resource<List<Hyouka>>>
    fun getFavoriteItems(): Flow<List<Hyouka>>
    fun setFavoriteItems(items: Hyouka, state: Boolean)
}