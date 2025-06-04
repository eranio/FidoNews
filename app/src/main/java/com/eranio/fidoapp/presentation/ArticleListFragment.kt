package com.eranio.fidoapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.eranio.fidoapp.Consts.SOURCE
import com.eranio.fidoapp.Consts.URL_FIELD
import com.eranio.fidoapp.databinding.FragmentArticleListBinding
import com.eranio.fidoapp.presentation.activity.ArticleDetailActivity
import com.eranio.fidoapp.presentation.adapter.ArticleAdapter
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {
    private lateinit var binding: FragmentArticleListBinding

    private lateinit var adapter: ArticleAdapter
    private val viewModel: ArticleViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ArticleListFragment", "Fragment view created")
        binding.recyclerViewArticles.layoutManager = LinearLayoutManager(requireContext())

        adapter = ArticleAdapter(onItemClick = { article ->
            val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
            intent.putExtra(URL_FIELD, article.url)
            startActivity(intent)
        })

        binding.recyclerViewArticles.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.articles
                .combine(viewModel.loading) { articles, isLoading ->
                    articles to isLoading
                }
                .collect { (articles, isLoading) ->
                    adapter.updateArticles(articles, isLoading)
                }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchArticles(SOURCE)
    }
}
