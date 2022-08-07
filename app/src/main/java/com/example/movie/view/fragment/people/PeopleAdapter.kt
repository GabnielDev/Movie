package com.example.movie.view.fragment.people

import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemPeopleBinding
import com.example.movie.remote.response.ItemPeople
import com.example.movie.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.fragment.people.PeopleAdapter.ViewHolder

class PeopleAdapter :
    BaseQuickAdapter<ItemPeople, ViewHolder>(
        R.layout.item_people
    ), LoadMoreModule {

    override fun convert(holder: ViewHolder, item: ItemPeople) {
        holder.bindData(item)
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemPeopleBinding.bind(view)

        fun bindData(item: ItemPeople) {
            binding.run {
                imgPhoto.load(BASE_URL_POSTER + item.profilePath)
                txtName.text = item.name
            }
        }

    }


}