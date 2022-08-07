package com.example.movie.view.fragment.home

import android.view.View
import android.view.animation.AnimationUtils
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemPosterBinding
import com.example.movie.remote.response.ResultsItem
import com.example.movie.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.fragment.home.HomeAdapter.ViewHolder
import koleton.api.loadSkeleton

class HomeAdapter
    : BaseQuickAdapter<ResultsItem, ViewHolder>
    (R.layout.item_poster) {

    override fun convert(holder: ViewHolder, item: ResultsItem) {
        holder.bindData(item)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.transition_animation)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemPosterBinding.bind(view)

        fun bindData(item: ResultsItem) {
            binding.run {
                imgPoster.load(BASE_URL_POSTER + item.posterPath)
            }
        }
    }
}
