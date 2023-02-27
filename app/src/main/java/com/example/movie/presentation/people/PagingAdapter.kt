package com.example.movie.view.fragment.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movie.databinding.ItemPeopleBinding
import com.example.movie.data.remote.response.ItemPeople
import com.example.movie.external.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.fragment.people.PagingAdapter.PagingViewHolder

class PagingAdapter(
    private val listener: OnClickListener
) : PagingDataAdapter<ItemPeople, PagingViewHolder>(PeopleDiffCallBack()) {

    interface OnClickListener {
        fun onItemClicked(item: ItemPeople?)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            ItemPeopleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listener
        )
    }

    class PagingViewHolder(
        val binding: ItemPeopleBinding,
        private val listener: OnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemPeople?) {
            binding.run {
                itemView.setOnClickListener {
                    listener.onItemClicked(item)
                }
                imgPhoto.load(BASE_URL_POSTER + item?.profilePath)
                txtName.text = item?.name
            }
        }
    }
}

class PeopleDiffCallBack : DiffUtil.ItemCallback<ItemPeople>() {
    override fun areItemsTheSame(oldItem: ItemPeople, newItem: ItemPeople): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemPeople, newItem: ItemPeople): Boolean {
        return oldItem.id == newItem.id
    }
}

