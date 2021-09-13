package com.interview.myarticlesapp.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.interview.myarticlesapp.R
import com.interview.myarticlesapp.adapters.ArticlesAdapter
import com.interview.myarticlesapp.models.Results
import com.interview.myarticlesapp.utils.Credentials
import com.interview.myarticlesapp.viewModelFactory.ArticleViewModelFactory
import com.interview.myarticlesapp.viewModels.ArticleViewModel


class MainActivity : AppCompatActivity() {
    lateinit var errorDisplay: TextInputLayout
    lateinit var progressBar: ProgressBar
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var articlesList: ArrayList<Results>

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        errorDisplay = findViewById(R.id.error_display_text_layout)
        progressBar = findViewById(R.id.retrieve_articles_progress_bar)
        articlesRecyclerView = findViewById(R.id.articles_list_recycler_view)
        toolBar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        articlesList = ArrayList()
        val articleViewModelFactory = ArticleViewModelFactory(errorDisplay, progressBar)
        articleViewModel = ViewModelProvider(this, articleViewModelFactory)
            .get(ArticleViewModel::class.java)
        fetchArticles()
    }

    private fun fetchArticles() {
        progressBar.visibility = View.VISIBLE
        articleViewModel.fetchArticles(7, Credentials.returnApiKey())
            .observe(this, {
                if (it != null) {
                    val articles = it.results
                    articlesList.addAll(articles)
                    initializeRecyclerView()
                }

            })
    }

    private fun initializeRecyclerView() {
        articlesAdapter = ArticlesAdapter(this, articlesList)
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesRecyclerView.setHasFixedSize(true)
        articlesRecyclerView.adapter = articlesAdapter

    }
}
