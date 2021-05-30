package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hyouka(
    val airing: Boolean=false,
    val episodes: Int=0,
    val image_url: String="",
    val mal_id: Int=0,
    val members: Int=0,
    val score: Double=0.0,
    val start_date: String="",
    val synopsis: String="",
    val title: String="",
    val type: String="",
    val isFavorite: Boolean=false
) : Parcelable
