package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.HyoukaResultResponse
import com.example.core.domain.model.Hyouka
import com.example.core.domain.respository.IHyoukaRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HyoukaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IHyoukaRepository {
    override fun getAllItems(): Flow<Resource<List<Hyouka>>> {
        return object : NetworkBoundResource<List<Hyouka>, List<HyoukaResultResponse>>() {
            override fun loadFromDB(): Flow<List<Hyouka>> {
                return localDataSource.getAllItem().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Hyouka>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<HyoukaResultResponse>>> =
                remoteDataSource.getList()


            override suspend fun saveCallResult(data: List<HyoukaResultResponse>) {
                val itemList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(itemList)
            }
        }.asFlow()
    }

    override fun getFavoriteItems(): Flow<List<Hyouka>> {
        return localDataSource.getFavoriteItem().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteItems(items: Hyouka, state: Boolean) {
        val hyoukaEntity = DataMapper.mapDomainToEntity(items)

        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTourism(hyoukaEntity, state)
        }
    }
}
