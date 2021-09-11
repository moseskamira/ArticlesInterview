package com.ndovu.myarticlesapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.ndovu.myarticlesapp.R
import com.ndovu.myarticlesapp.models.ArticleResponse
import com.ndovu.myarticlesapp.viewModelFactory.ArticleViewModelFactory
import com.ndovu.myarticlesapp.viewModels.ArticleViewModel

class MainActivity : AppCompatActivity() {
    lateinit var errorDisplay: TextInputLayout
    lateinit var progressBar: ProgressBar
    private lateinit var articleViewModel: ArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        errorDisplay = findViewById(R.id.error_display_text_layout)
        progressBar = findViewById(R.id.retrieve_articles_progress_bar)


        val articleViewModelFactory = ArticleViewModelFactory(errorDisplay, progressBar)
        articleViewModel = ViewModelProvider(this, articleViewModelFactory)
            .get(ArticleViewModel::class.java)
        fetchArticles()
    }

    private fun fetchArticles() {
        progressBar.visibility = View.VISIBLE
        articleViewModel.fetchArticles(7, "RREDo9ObCv9Smd9FqH45wcnXZ5RXUE9K")
            .observe(this, {
                if (it != null) {
                    Log.d("FIRSTARTICLE", it.results[1].abstract)
                } else {
                    Log.d("SSSS", "HERE")
                }

            })
    }
}
