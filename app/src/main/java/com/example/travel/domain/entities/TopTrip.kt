package com.example.travel.domain.entities


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopTrip(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isLiked")
    val isLiked: Boolean,
    @SerializedName("price")
    val price: Int,
    @SerializedName("review_count")
    val reviewCount: Int,
    @SerializedName("time")
    val time: Double,
    @SerializedName("title")
    val title: String
)