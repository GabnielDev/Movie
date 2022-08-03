package com.example.movie.adapter

import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.adapter.VideoAdapter.ViewHolder
import com.example.movie.databinding.ItemPosterBinding
import com.example.movie.databinding.ItemYoutubeBinding
import com.example.movie.helper.buildYouTubeThumbnailURL
import com.example.movie.remote.response.ResultTrailer

class VideoAdapter: BaseQuickAdapter<ResultTrailer, ViewHolder>(R.layout.item_youtube) {

    override fun convert(holder: ViewHolder, item: ResultTrailer) {
        holder.bindData(item)
    }

    class ViewHolder(view: View): BaseViewHolder(view) {
        private val binding = ItemYoutubeBinding.bind(view)

        fun bindData(data:ResultTrailer) {
            binding.run {
                videoYoutube.setBackgroundResource(R.drawable.ic_home)
//                videoYoutube.load(data.key?.let { buildYouTubeThumbnailURL(it) })
            }
        }

    }


}