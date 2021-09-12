package com.ndovu.myarticlesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.ndovu.myarticlesapp.R
import com.ndovu.myarticlesapp.models.Results
import com.ndovu.myarticlesapp.views.ArticleDetailsActivity

class ArticlesAdapter(private val context: Context, private val dataSet: ArrayList<Results>) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.articles_list_view,
            viewGroup, false
        )
        val articlesParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = articlesParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val articleTitle = dataSet[position].title
        val abstract = dataSet[position].abstract
        val articleCreatedAt = dataSet[position].published_date
//        val imageUrl = dataSet[position]
        viewHolder.articleTitleTextView.text = articleTitle
        viewHolder.authorTextView.text = abstract
        viewHolder.articleCreatedAtTextView.text = "Published At: $articleCreatedAt"
//        Glide.with(context).asBitmap().load(imageUrl).into(viewHolder.imageView)


        viewHolder.customView.setOnClickListener {
            val moveToDetailIntent = Intent(it.context, ArticleDetailsActivity()::class.java)
            moveToDetailIntent.putExtra("updatedAt", dataSet[position].updated)
            moveToDetailIntent.putExtra("source", dataSet[position].source)
            moveToDetailIntent.putExtra("section", dataSet[position].section)
            moveToDetailIntent.putExtra("subsection", dataSet[position].subsection)
            moveToDetailIntent.putExtra("type", dataSet[position].type)
            moveToDetailIntent.putExtra("title", dataSet[position].title)
            moveToDetailIntent.putExtra("abstract", abstract)
            moveToDetailIntent.putExtra("createdAt", articleCreatedAt)
            it.context.startActivity(moveToDetailIntent)
        }

    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.article_image)
        val articleTitleTextView: TextView = view.findViewById(R.id.article_title)
        val authorTextView: TextView = view.findViewById(R.id.article_author)
        val customView: LinearLayoutCompat = view.findViewById(R.id.articles_list_view_id)
        val articleCreatedAtTextView: TextView = view.findViewById(R.id.article_created_at)

    }

}