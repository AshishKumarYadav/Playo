package com.ashish.playo.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.playo.databinding.RowLayoutBinding
import com.ashish.playo.model.NewsArticle
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var arrayList: MutableList<NewsArticle>? =null

    fun setNewsList(news: List<NewsArticle>) {
        this.arrayList = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLayoutBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = arrayList?.get(position)
        holder.binding.newsAuthor.text = item?.author
        holder.binding.newsTitle.text = item?.title
        holder.binding.newsPublishedAt.text = item?.publishedAt

        Glide.with(holder.itemView.context).load(item?.urlToImage).into(holder.binding.newsImage)
        holder.binding.newsImage.adjustViewBounds=true
        holder.binding.newsCard.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(item?.url)
            context.startActivity(i)

        })

    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    class NewsViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

}