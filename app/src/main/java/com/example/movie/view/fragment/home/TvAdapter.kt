package com.example.movie.view.fragment.home

import android.view.View
import android.view.animation.AnimationUtils
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemPosterBinding
import com.example.movie.remote.response.ResultTv
import com.example.movie.utils.Constants.BASE_URL_POSTER

class TvAdapter
    : BaseQuickAdapter<ResultTv, TvAdapter.ViewHolder>
    (R.layout.item_poster) {

    override fun convert(holder: ViewHolder, item: ResultTv) {
        holder.bindData(item)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.transition_animation)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemPosterBinding.bind(view)

        fun bindData(item: ResultTv) {
            binding.run {
                imgPoster.load(BASE_URL_POSTER + item.posterPath)
            }
        }
    }


}