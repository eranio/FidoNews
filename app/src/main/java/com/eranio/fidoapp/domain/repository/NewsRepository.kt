package com.eranio.fidoapp.domain.repository

import com.eranio.fidoapp.domain.model.Article

interface NewsRepository {
    suspend fun getTopHeadlines(source: String): List<Article>
}