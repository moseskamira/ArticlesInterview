package com.ndovu.myarticlesapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ndovu.myarticlesapp.R

class ArticleDetailsActivity : AppCompatActivity() {
    private lateinit var articleDetailsBundle: Bundle
    private lateinit var articleSourceTv: TextView
    private lateinit var articleSectionTv: TextView
    private lateinit var articleSubSectionTv: TextView
    private lateinit var articleTitleTv: TextView
    private lateinit var abstractTv: TextView
    private lateinit var articleCreatedAtTv: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        articleDetailsBundle = intent.extras!!
        articleSourceTv = findViewById(R.id.article_source_detail)
        articleSectionTv = findViewById(R.id.article_section_detail)
        articleSubSectionTv = findViewById(R.id.article_sub_section_detail)
        articleTitleTv = findViewById(R.id.article_title_detail)
        abstractTv = findViewById(R.id.article_abstract_detail)
        articleCreatedAtTv = findViewById(R.id.article_created_at_detail)
        retrieveBundleData()
    }

    private fun retrieveBundleData() {
        val updatedAt = articleDetailsBundle.getString("updatedAt")!!
        val source = articleDetailsBundle.getString("source")!!
        val section = articleDetailsBundle.getString("section")!!
        val subsection = articleDetailsBundle.getString("subsection")!!
        val title = articleDetailsBundle.getString("title")!!
        val abstract = articleDetailsBundle.getString("abstract")!!
        val createdAt = articleDetailsBundle.getString("createdAt")!!

        articleTitleTv.text = title
        abstractTv.text = abstract
        articleCreatedAtTv.text = createdAt
        articleSourceTv.text = source
        articleSectionTv.text = section
        if (subsection.isNotEmpty()) {
            articleSubSectionTv.text = subsection
        } else {
            articleSubSectionTv.text = "N/A"
        }


    }
}