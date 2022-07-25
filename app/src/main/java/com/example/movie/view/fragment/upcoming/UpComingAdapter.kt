package com.example.movie.view.fragment.upcoming

import android.view.View
import android.view.animation.AnimationUtils
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemUpcomingBinding
import com.example.movie.remote.response.ResultsItem
import com.example.movie.utils.Constants.BASE_URL_BACKPOSTER

class UpComingAdapter
    : BaseQuickAdapter<ResultsItem, UpComingAdapter.UpComingViewBinding>
    (R.layout.item_upcoming), LoadMoreModule {

    override fun convert(holder: UpComingViewBinding, item: ResultsItem) {
        holder.bindData(item)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bottom_transition)
    }

    inner class UpComingViewBinding(view: View) : BaseViewHolder(view) {
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