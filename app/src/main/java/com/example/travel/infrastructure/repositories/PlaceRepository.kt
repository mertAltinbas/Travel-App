package com.example.travel.infrastructure.repositories

import android.util.Log
import com.example.travel.core.base.callback.HttpCallBack
import com.example.travel.core.base.exception.CustomHttpException
import com.example.travel.domain.entities.HomePageFeed
import com.example.travel.domain.repositories.IPlaceRepository
import com.example.travel.infrastructure.data_source.place.LocalPlaceDataSource
import com.example.travel.infrastructure.data_source.place.RemotePlaceDataSource
import retrofit2.Call
import retrofit2.HttpException

class PlaceRepository(
    private val localDataSource : LocalPlaceDataSource,
    private val remoteDataSource : RemotePlaceDataSource,
) : IPlaceRepository {

    override suspend fun getHomePage() : HomePageFeed {
        return remoteDataSource.getHomePage()
    }

//    override suspend fun getHomePage(callback: HttpCallBack<HomePageFeed>) {
//        try {
//            val data = remoteDataSource.getHomePage()
//            callback.onSuccess(data)
//        }catch(e: HttpException) {
//            callback.onFailure(CustomHttpException(statusCode= e.code(), body = e.response()?.body(), message = e.message()))
//        }catch(e:Exception) {
//            callback.onFailure(CustomHttpException(statusCode = -1, body = null, message = e.message))
//        }
//    }
}