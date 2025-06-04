package com.eranio.fidoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eranio.fidoapp.R
import com.eranio.fidoapp.databinding.ItemArticleBinding
import com.eranio.fidoapp.databinding.ItemLoadingBinding
import com.eranio.fidoapp.domain.model.Article

sealed class ArticleListItem {
    data class ArticleItem(val article: Article) : ArticleListItem()
    object LoadingItem : ArticleListItem()
}

class ArticleAdapter(
    private val onItemClick: (Article) -> Unit
) : ListAdapter<ArticleListItem, RecyclerView.ViewHolder>(DiffCallback) {

    companion object {
        private const val VIEW_TYPE_ARTICLE = 0
        private const val VIEW_TYPE_LOADING = 1

        private val DiffCallback = object : DiffUtil.ItemCallback<ArticleListItem>() {
            override fun areItemsTheSame(oldItem: ArticleListItem, newItem: ArticleListItem): Boolean {
                return when {
                    oldItem is ArticleListItem.ArticleItem && newItem is ArticleListItem.ArticleItem ->
                        oldItem.article.url == newItem.article.url
                    oldItem is ArticleListItem.LoadingItem && newItem is ArticleListItem.LoadingItem -> true
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: ArticleListItem, newItem: ArticleListItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun updateArticles(articles: List<Article>, isLoading: Boolean) {
        val newList = mutableListOf<ArticleListItem>()
        if (isLoading) newList.add(ArticleListItem.LoadingItem)
        newList.addAll(articles.map { ArticleListItem.ArticleItem(it) })
        submitList(newList)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ArticleListItem.LoadingItem -> VIEW_TYPE_LOADING
            is ArticleListItem.ArticleItem -> VIEW_TYPE_ARTICLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_LOADING) {
            val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LoadingViewHolder(binding)
        } else {
            val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ArticleViewHolder(binding)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            val item = getItem(position) as ArticleListItem.ArticleItem
            holder.bind(item.article, onItemClick)
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, onItemClick: (Article) -> Unit) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description ?: ""
            binding.root.setOnClickListener { onItemClick(article) }
            Glide.with(binding.imageViewThumbnail.context)
                .load(article.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewThumbnail)
        }
    }

    inner class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)
}
