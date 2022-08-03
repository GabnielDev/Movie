package com.example.movie.view.fragment.search

import android.view.View
import android.view.animation.AnimationUtils
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemPosterSearchBinding
import com.example.movie.remote.response.ResultsItem
import com.example.movie.utils.Constants
import com.example.movie.view.fragment.search.SearchAdapter.ViewHolder

class SearchAdapter :
    BaseQuickAdapter<ResultsItem, ViewHolder>(
        R.layout.item_poster_search
    ) {

    override fun convert(holder: ViewHolder, item: ResultsItem) {
        holder.bindData(item)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bottom_transition)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemPosterSearchBinding.bind(view)

        fun bindData(item: ResultsItem) {
            with(binding) {
                imgGrid.load(Constants.BASE_URL_POSTER + item.posterPath)
            }
        }

    }
}