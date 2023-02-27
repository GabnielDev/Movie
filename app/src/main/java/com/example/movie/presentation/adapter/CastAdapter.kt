package com.example.movie.presentation.adapter

import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.presentation.adapter.CastAdapter.ViewHolder
import com.example.movie.databinding.ItemCreditsPeopleBinding
import com.example.movie.data.remote.response.CastItem
import com.example.movie.external.utils.Constants.BASE_URL_POSTER

class CastAdapter :
    BaseQuickAdapter<CastItem, ViewHolder>(
        R.layout.item_credits_people
    ) {

    override fun convert(holder: ViewHolder, item: CastItem) {
        holder.binData(item)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemCreditsPeopleBinding.bind(view)

        fun binData(item: CastItem) {
            binding.run {
                imgCast.load(BASE_URL_POSTER + item.profile_path)
                txtCharacter.text = item.character
                txtName.text = item.name
            }
        }
    }

}