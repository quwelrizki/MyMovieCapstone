package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.HyoukaResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/search/anime?q=hyouka")
    suspend fun getList(): HyoukaResponse
}