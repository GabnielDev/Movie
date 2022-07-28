package com.example.movie.view.fragment.upcoming

import android.view.View
import android.view.animation.AnimationUtils
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemUpcomingBinding
import com.example.movie.remote.response.ResultsItem
import com.example.movie.utils.Constants.BASE_URL_BACKPOSTER
import com.example.movie.view.fragment.upcoming.UpComingAdapter.ViewHolder

class UpComingAdapter
    : BaseQuickAdapter<ResultsItem, ViewHolder>(R.layout.item_upcoming) {

    override fun convert(holder: ViewHolder, item: ResultsItem) {
        holder.bindData(item)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bottom_transition)
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemUpcomingBinding.bind(view)

        fun bindData(item: ResultsItem) {
            binding.run {
                imgPoster.load(BASE_URL_BACKPOSTER + item.backdropPath)
                txtJudulPertama.text = item.originalTitle
                txtJudul.text = item.title
                txtDesc.text = item.overview
            }
        }
    }

}