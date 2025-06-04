package com.eranio.fidoapp.presentation

import android.util.Log
import androidx.lifecycle.*
import com.eranio.fidoapp.domain.model.Article
import com.eranio.fidoapp.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    private val _loading = MutableStateFlow(false)

    val articles: StateFlow<List<Article>> = _articles
    val loading: StateFlow<Boolean> = _loading

    fun fetchArticles(source: String) {
        Log.d("ArticleViewModel", "Fetching articles for source: $source")
        viewModelScope.launch {
            try {
                _loading.value = true
                val articlesList = getArticlesUseCase(source)
                _articles.value = articlesList
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}
