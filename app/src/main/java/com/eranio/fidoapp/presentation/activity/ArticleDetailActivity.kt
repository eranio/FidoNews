package com.eranio.fidoapp.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eranio.fidoapp.Consts.URL_FIELD
import com.eranio.fidoapp.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val url = intent.getStringExtra(URL_FIELD)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url ?: "")
    }
}