package com.example.movie.adapter

import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.adapter.CrewAdapter.ViewHolder
import com.example.movie.databinding.ItemCreditsPeopleBinding
import com.example.movie.remote.response.CrewItem
import com.example.movie.utils.Constants

class CrewAdapter :
    BaseQuickAdapter<CrewItem, ViewHolder>(
        R.layout.item_credits_people
    ) {

    override fun convert(holder: ViewHolder, item: CrewItem) {
        holder.bindData(item)
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemCreditsPeopleBinding.bind(view)

        fun bindData(item: CrewItem) {
            binding.run {
                imgCast.load(Constants.BASE_URL_POSTER + item.profilePath)
                txtCharacter.text = item.job
                txtName.text = item.name
            }
        }

    }


}