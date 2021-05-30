package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.HyoukaResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    fun getList(): Flow<ApiResponse<List<HyoukaResultResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataList = response.results
                if (dataList.isNotEmpty()){
                    emit(ApiResponse.Success(dataList))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e :Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Error Exception",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }
}