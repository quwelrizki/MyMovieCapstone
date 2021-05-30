package com.example.core.data.source.local.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.HyoukaEntity

@Database(entities = [HyoukaEntity::class], version = 1, exportSchema = false)
abstract class HyoukaDatabase: RoomDatabase(){
    abstract fun hyoukaDao() : HyoukaDao
}