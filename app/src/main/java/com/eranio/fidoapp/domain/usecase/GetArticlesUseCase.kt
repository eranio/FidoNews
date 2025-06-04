package com.eranio.fidoapp.domain.usecase

import com.eranio.fidoapp.domain.repository.NewsRepository

class GetArticlesUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(source: String) = repository.getTopHeadlines(source)
}
