package com.example.newsapp.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import com.example.newsapp.R
import com.example.newsapp.databinding.ListItemBinding
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.News
import com.example.newsapp.utils.snackBar


class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items =
        News(ArrayList())

    fun setDataList(results: List<Article>) {
        this.items.articles = results
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val withDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsViewHolder.LAYOUT,
            parent,
            false
        )
        return NewsViewHolder(withDataBinding)
    }


    override fun getItemCount() = items.articles.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = items.articles[position]

        holder.viewDataBinding.apply {
            title.text = news.title
            publicationDate.text = news.publishedAt
            author.text = "Source : ${news.source.name}"
            description.text = news.description
        }


        Glide.with(holder.itemView.context).load(news.urlToImage).apply(
            centerCropTransform()
                .placeholder(R.drawable.nophotos)
                .error(R.drawable.nophotos)
        )
            .into(holder.viewDataBinding.imageLeft)

        holder.viewDataBinding.openWebView.setOnClickListener {
            //open web view onClicked
            try {
                val uri: Uri = Uri.parse(news.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                it.context.startActivity(
                    Intent(
                        intent
                    )
                )
            } catch (e: Exception) {
                it.snackBar("Could not open link")
            }
        }
    }

    class NewsViewHolder(val viewDataBinding: ListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_item
        }
    }

}