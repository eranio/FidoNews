package com.eranio.fidoapp.domain.model

data class Article(
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val publishedAt: String,
    val url: String
)
