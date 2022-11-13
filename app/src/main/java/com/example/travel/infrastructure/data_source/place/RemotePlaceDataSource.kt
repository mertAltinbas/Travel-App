package com.example.travel.infrastructure.data_source.place

import com.example.travel.core.constants.AppConstants
import com.example.travel.domain.entities.HomePageFeed
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val getHomePagePath = "n6nGRUAR?token=TBn71OPai32OiJWZul2tEA"

interface RemotePlaceDataSource {
    @GET(getHomePagePath)
    suspend fun getHomePage(): HomePageFeed


    companion object {
        private var service: RemotePlaceDataSource? = null

        fun getInstance(): RemotePlaceDataSource {
            if (service == null) {
                service = Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder()
                                .setLenient()
                                .create()))
                    .build().create(RemotePlaceDataSource::class.java)
            }
            return service!!
        }
    }
}