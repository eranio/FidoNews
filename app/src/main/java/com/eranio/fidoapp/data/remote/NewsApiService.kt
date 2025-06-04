package com.eranio.fidoapp.data.remote

import com.eranio.fidoapp.data.remote.model.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): NewsResponseDto
}
