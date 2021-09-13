package com.interview.myarticlesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.interview.myarticlesapp.R
import com.interview.myarticlesapp.models.Results
import com.interview.myarticlesapp.views.ArticleDetailsActivity

class ArticlesAdapter(private val context: Context, private var dataSet: ArrayList<Results>) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(), Filterable {
    private var articleFilterList: ArrayList<Results> = dataSet

    init {
        articleFilterList = dataSet
    }

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
        val articleTitle = articleFilterList[position].title
        val abstract = articleFilterList[position].abstract
        val articleCreatedAt = articleFilterList[position].published_date
        viewHolder.articleTitleTextView.text = articleTitle
        viewHolder.authorTextView.text = abstract
        viewHolder.articleCreatedAtTextView.text = "Published At: $articleCreatedAt"

        viewHolder.customView.setOnClickListener {
            val moveToDetailIntent = Intent(it.context, ArticleDetailsActivity()::class.java)
            moveToDetailIntent.putExtra("updatedAt", articleFilterList[position].updated)
            moveToDetailIntent.putExtra("source", articleFilterList[position].source)
            moveToDetailIntent.putExtra("section", articleFilterList[position].section)
            moveToDetailIntent.putExtra("subsection", articleFilterList[position].subsection)
            moveToDetailIntent.putExtra("type", articleFilterList[position].type)
            moveToDetailIntent.putExtra("title", articleFilterList[position].title)
            moveToDetailIntent.putExtra("abstract", abstract)
            moveToDetailIntent.putExtra("createdAt", articleCreatedAt)
            it.context.startActivity(moveToDetailIntent)
        }

    }

    override fun getItemCount() = articleFilterList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.article_image)
        val articleTitleTextView: TextView = view.findViewById(R.id.article_title)
        val authorTextView: TextView = view.findViewById(R.id.article_author)
        val customView: LinearLayoutCompat = view.findViewById(R.id.articles_list_view_id)
        val articleCreatedAtTextView: TextView = view.findViewById(R.id.article_created_at)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint == null || constraint.isEmpty()) {
                    articleFilterList.addAll(dataSet)
                } else {
                    for (article in dataSet) {
                        if (article.title.toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
                            articleFilterList.add(article)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = articleFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                articleFilterList = results?.values as ArrayList<Results>
                notifyDataSetChanged()
            }
        }
    }


}