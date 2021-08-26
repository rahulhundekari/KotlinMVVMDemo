package com.example.demoassignment.di

import com.example.demoassignment.network.NYTimesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

//    @Provides
//    @ViewModelScoped
//    fun provideNYTimesApi(retrofit: Retrofit): NYTimesApi {
//        return retrofit.create(NYTimesApi::class.java)
//    }


}