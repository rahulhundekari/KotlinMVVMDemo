package com.example.demoassignment.data.repository

import android.util.Log
import com.example.demoassignment.di.NetworkModule
import com.example.demoassignment.network.NYTimesApi
import com.example.demoassignment.network.entities.MostViewedApiResponse
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.Credentials
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class FetchDataRepository @Inject constructor(
    private val nyTimesApi: NYTimesApi,
) {


    suspend fun fetchMostPopularData(period: Int) =
        nyTimesApi.getMostPopularViewedApi(
            period,
            NetworkModule.API_KEY
        )
}