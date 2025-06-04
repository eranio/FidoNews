package com.eranio.fidoapp.data.remote.model

import com.eranio.fidoapp.domain.model.Article

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) {
    fun toDomain(): Article {
        return Article(
            title = title.orEmpty(),
            description = description,
            imageUrl = urlToImage,
            publishedAt = publishedAt,
            url = url
        )
    }
}

data class SourceDto(
    val id: String?,
    val name: String
)
