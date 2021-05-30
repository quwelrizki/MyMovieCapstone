package com.example.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "hyouka")
data class HyoukaEntity(
    val airing: Boolean=false,
    val episodes: Int=0,
    val image_url: String="",
    @PrimaryKey
    val mal_id: Int=0,
    val members: Int=0,
    val score: Double=0.0,
    val start_date: String="",
    val synopsis: String="",
    val title: String="",
    val type: String="",
    var isFavorite: Boolean = false
): Parcelable