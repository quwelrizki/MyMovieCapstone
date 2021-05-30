package com.example.core.data.source.remote.response

data class HyoukaResponse(
    val last_page: Int=0,
    val results: List<HyoukaResultResponse>
)