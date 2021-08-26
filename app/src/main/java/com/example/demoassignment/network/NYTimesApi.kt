package com.example.demoassignment.network

import com.example.demoassignment.network.entities.MostViewedApiResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesApi {

    @POST("viewed/{period}.json")
    suspend fun getMostPopularViewedApi(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Response<MostViewedApiResponse>

}