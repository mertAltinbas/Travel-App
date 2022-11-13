package com.example.travel.domain.entities

import com.example.travel.domain.entities.Category
import com.example.travel.domain.entities.TopTrip
import com.google.gson.annotations.SerializedName

class HomePageFeed(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("slider_images")
    val sliderImages: List<String>,
    @SerializedName("top_trips")
    val topTrips: List<TopTrip>
)