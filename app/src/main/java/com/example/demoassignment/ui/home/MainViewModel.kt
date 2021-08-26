package com.example.demoassignment.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoassignment.data.repository.FetchDataRepository
import com.example.demoassignment.di.NetworkModule
import com.example.demoassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchDataRepository: FetchDataRepository
) :
    ViewModel() {


    fun getUserData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            val data = fetchDataRepository.fetchMostPopularData(1)

            when (data.body()?.status) {
                NetworkModule.STATUS_SUCCESS -> {
                    emit(Resource.success(data = data.body()?.results))
                }
                else -> {
                    emit(Resource.error(data = null, message = "Error Occurred! Please try again."))
                }
            }

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}