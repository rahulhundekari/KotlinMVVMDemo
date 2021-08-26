package com.example.demoassignment.network.entities

import com.example.demoassignment.data.module.Result
import com.google.gson.annotations.SerializedName

data class MostViewedApiResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("status")
    val status: String
)
