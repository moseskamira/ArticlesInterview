package com.ndovu.myarticlesapp.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.ndovu.myarticlesapp.R
import com.ndovu.myarticlesapp.adapters.ArticlesAdapter
import com.ndovu.myarticlesapp.models.Results
import com.ndovu.myarticlesapp.viewModelFactory.ArticleViewModelFactory
import com.ndovu.myarticlesapp.viewModels.ArticleViewModel


class MainActivity : AppCompatActivity() {
    lateinit var errorDisplay: TextInputLayout
    lateinit var progressBar: ProgressBar
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var articlesList: ArrayList<Results>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        errorDisplay = findViewById(R.id.error_display_text_layout)
        progressBar = findViewById(R.id.retrieve_articles_progress_bar)
        articlesRecyclerView = findViewById(R.id.articles_list_recycler_view)
        articlesList = ArrayList()


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
                    val articles = it.results
                    articlesList.addAll(articles)
                    initializeRecyclerView()
                } else {
                    Log.d("SSSS", "HERE")
                }

            })
    }
    private fun initializeRecyclerView() {
        articlesAdapter = ArticlesAdapter(this, articlesList)
        articlesAdapter.notifyDataSetChanged()
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        articlesRecyclerView.setHasFixedSize(true)
        articlesRecyclerView.adapter = articlesAdapter

    }
}
