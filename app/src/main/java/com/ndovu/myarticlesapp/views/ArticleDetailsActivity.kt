package com.ndovu.myarticlesapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ndovu.myarticlesapp.R

class ArticleDetailsActivity : AppCompatActivity() {
    private lateinit var articleDetailsBundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        articleDetailsBundle = intent.extras!!
        retrieveBundleData()

    }


    private fun retrieveBundleData() {
        val type = articleDetailsBundle.getString("type", null)
        val updatedAt = articleDetailsBundle.getString("updatedAt", null)
        val source = articleDetailsBundle.getString("source", null)
        val section = articleDetailsBundle.getString("section", null)
        val subsection = articleDetailsBundle.getString("subsection", null)
        if (type != null) {
            Log.d("ARTICLESECTION", section)
        }


    }
}