package com.example.movie.view.activity.detail

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemGenreBinding
import com.example.movie.remote.response.GenresItem
import com.example.movie.view.activity.detail.GenreDetailAdapter.ViewHolder

class GenreDetailAdapter
    : BaseQuickAdapter<GenresItem, ViewHolder>(R.layout.item_genre) {

    override fun convert(holder: ViewHolder, item: GenresItem) {
        holder.bindData(item)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemGenreBinding.bind(view)

        fun bindData(item: GenresItem) {
            binding.run {
                txtGenres.text = item.name
            }
        }
    }

}