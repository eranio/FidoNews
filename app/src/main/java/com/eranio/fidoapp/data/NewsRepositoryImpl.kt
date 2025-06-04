package com.eranio.fidoapp.data

import android.util.Log
import com.eranio.fidoapp.Consts.API_KEY
import com.eranio.fidoapp.data.remote.NewsApiService
import com.eranio.fidoapp.domain.model.Article
import com.eranio.fidoapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsApiService
) : NewsRepository {

    override suspend fun getTopHeadlines(source: String): List<Article> {
        val response = api.getTopHeadlines(
            source = source,
            apiKey = API_KEY
        )
        Log.d("NewsRepository", "API Response: ${response.articles.size} articles fetched")

        return response.articles.map { it.toDomain() }
    }
}
