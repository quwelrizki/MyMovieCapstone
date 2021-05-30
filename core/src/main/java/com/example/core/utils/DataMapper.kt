package com.example.core.utils

import com.example.core.data.source.local.entity.HyoukaEntity
import com.example.core.data.source.remote.response.HyoukaResultResponse
import com.example.core.domain.model.Hyouka

object DataMapper {
    fun mapResponsesToEntities(input: List<HyoukaResultResponse>): List<HyoukaEntity> {
        val itemList = ArrayList<HyoukaEntity>()
        input.map {
            val item =   HyoukaEntity(
                airing = it.airing,
                episodes = it.episodes,
                image_url = it.image_url,
                mal_id = it.mal_id,
                members = it.members,
                score = it.score,
                start_date = it.start_date,
                synopsis = it.synopsis,
                title = it.title,
                type = it.type,
                isFavorite = false

            )
            itemList.add(item)
        }
        return itemList
    }

    fun mapEntitiesToDomain(input: List<HyoukaEntity>): List<Hyouka> =
        input.map {
            Hyouka(
                airing = it.airing,
                episodes = it.episodes,
                image_url = it.image_url,
                mal_id = it.mal_id,
                members = it.members,
                score = it.score,
                start_date = it.start_date,
                synopsis = it.synopsis,
                title = it.title,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: Hyouka) = HyoukaEntity(
        airing = input.airing,
        episodes = input.episodes,
        image_url = input.image_url,
        mal_id = input.mal_id,
        members = input.members,
        score = input.score,
        start_date = input.start_date,
        synopsis = input.synopsis,
        title = input.title,
        type = input.type,
        isFavorite = input.isFavorite

    )
}