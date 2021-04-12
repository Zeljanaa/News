package com.example.newsapp.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.CategoriesItemBinding
import com.example.newsapp.domain.categories.Cateogory
import com.example.newsapp.domain.categories.Source
import com.example.newsapp.utils.snackBar


class CategoryAdapter() :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var items =
        Cateogory(ArrayList())

    fun setDataList(results: List<Source>) {
        this.items.sources = results
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val withDataBinding: CategoriesItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CategoryViewHolder.LAYOUT,
            parent,
            false
        )
        return CategoryViewHolder(withDataBinding)
    }


    override fun getItemCount() = items.sources.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val category = items.sources[position]

        holder.viewDataBinding.apply {
            this.category.text = "CATEGORY : ${category.category}"
            name.text = "Source : ${category.name}"
            description.text = category.description
        }

        holder.viewDataBinding.openWebView.setOnClickListener {
            //open web view onClicked
            try {
                val uri: Uri = Uri.parse(category.url)
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

    class CategoryViewHolder(val viewDataBinding: CategoriesItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.categories_item
        }
    }

}