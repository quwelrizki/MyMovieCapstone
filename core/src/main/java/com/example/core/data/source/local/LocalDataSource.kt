package com.example.core.data.source.local

import com.example.core.data.source.local.entity.HyoukaEntity
import com.example.core.data.source.local.room.HyoukaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val hyoukaDao: HyoukaDao) {
    fun getAllItem(): Flow<List<HyoukaEntity>> = hyoukaDao.getAllItems()

    fun getFavoriteItem(): Flow<List<HyoukaEntity>> = hyoukaDao.getFavoriteItems()

    suspend fun insertTourism(item: List<HyoukaEntity>) = hyoukaDao.insertItem(item)

    fun setFavoriteTourism(item: HyoukaEntity, newState: Boolean) {
        item.isFavorite = newState
        hyoukaDao.updateFavoriteItem(item)
    }
}