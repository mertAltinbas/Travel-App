package com.example.travel.domain.repositories

import com.example.travel.domain.entities.HomePageFeed

interface IPlaceRepository {
    suspend fun getHomePage():HomePageFeed
    // suspend fun getHomePage(callback: HttpCallBack<HomePageFeed>)
}