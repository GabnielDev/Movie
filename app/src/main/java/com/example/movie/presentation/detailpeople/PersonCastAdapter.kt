package com.example.movie.presentation.detailpeople

import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemPosterPeopleBinding
import com.example.movie.data.remote.response.ItemCast
import com.example.movie.external.utils.Constants.BASE_URL_POSTER
import com.example.movie.presentation.detailpeople.PersonCastAdapter.ViewHolder

class PersonCastAdapter :
    BaseQuickAdapter<ItemCast, ViewHolder>(
        R.layout.item_poster_people
    ) {
    override fun convert(holder: ViewHolder, item: ItemCast) {
        holder.bindData(item)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemPosterPeopleBinding.bind(view)

        fun bindData(item: ItemCast) {
            binding.run {
                imgFilm.load(BASE_URL_POSTER + item.posterPath)
                txtAs.text = item.character
            }
        }

    }


}