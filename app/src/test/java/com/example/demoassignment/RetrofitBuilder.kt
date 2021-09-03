package com.example.demoassignment

import com.example.demoassignment.network.NYTimesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val nyTimesApi: NYTimesApi = getRetrofit().create(NYTimesApi::class.java)


}