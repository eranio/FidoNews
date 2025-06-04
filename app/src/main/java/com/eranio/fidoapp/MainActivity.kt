package com.eranio.fidoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eranio.fidoapp.databinding.ActivityMainBinding
import com.eranio.fidoapp.presentation.ArticleListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the ArticleListFragment as the default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, ArticleListFragment())
                .commit()
        }
    }
}
