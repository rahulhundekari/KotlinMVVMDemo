package com.example.demoassignment.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.demoassignment.MockResponseFileReader
import com.example.demoassignment.RetrofitBuilder
import com.example.demoassignment.data.module.Result
import com.example.demoassignment.data.repository.FetchDataRepository
import com.example.demoassignment.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var fetchDataRepository: FetchDataRepository

    @Mock
    private lateinit var apiObserver: Observer<Resource<List<Result>?>>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fetchDataRepository = FetchDataRepository(RetrofitBuilder.nyTimesApi)
        mainViewModel = MainViewModel(fetchDataRepository)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `read sample success json file`() {
        val reader = MockResponseFileReader("success_response.json")
        assertNotNull(reader.content)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `fetch details and check response Code 200 returned`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = fetchDataRepository.fetchMostPopularData(1)

//        val actualResponse = mainViewModel.getUserData()
        // Assert
        assertEquals(
            response.toString().contains("200"),
            actualResponse.code().toString().contains("200")
        )
    }

    @Test
    fun `fetch details and check response success returned`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        // Act
        val actualResponse = fetchDataRepository.fetchMostPopularData(1)

        // Assert
        assertEquals(
            mockResponse?.let { `parse mocked JSON response`(it) },
            actualResponse.body()?.status
        )
    }

    private fun `parse mocked JSON response`(mockResponse: String): String {
        val reader = JSONObject(mockResponse)
        return reader.getString("status")
    }

    @Test
    fun `fetch details for failed response 400 returned`() = runBlocking {
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val actualResponse = fetchDataRepository.fetchMostPopularData(1)

        // Assert
        assertNotEquals(
            response.toString().contains("400"),
            actualResponse.toString().contains("400")
        )
    }

    @After
    fun tearDown() {
//        mainViewModel.getUserData().removeObserver(apiObserver)
        mockWebServer.shutdown()
    }
}