package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.HyoukaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HyoukaDao {
    @Query("SELECT * FROM hyouka")
    fun getAllItems(): Flow<List<HyoukaEntity>>

    @Query("SELECT * FROM hyouka where isFavorite = 1")
    fun getFavoriteItems(): Flow<List<HyoukaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(onePiece: List<HyoukaEntity>)

    @Update
    fun updateFavoriteItem(onePiece: HyoukaEntity)
}