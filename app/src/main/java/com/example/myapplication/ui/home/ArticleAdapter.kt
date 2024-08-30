package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ViewAlbumBinding
import com.example.myapplication.model.Article

class ArticleAdapter( private val articles: List<Article>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {

    inner class ArticleAdapterViewHolder(val binding: ViewAlbumBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleAdapterViewHolder(
        ViewAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = articles.size


    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        holder.binding.txtTitle.text = articles[position].title
        holder.itemView.setOnClickListener {
            onClick.invoke(position)
        }
    }
}