package com.example.movie.presentation.detailmovie

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemGenreBinding
import com.example.movie.data.remote.response.GenresItem
import com.example.movie.presentation.detailmovie.GenreDetailAdapter.ViewHolder

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