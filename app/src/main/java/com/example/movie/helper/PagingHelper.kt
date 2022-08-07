package com.example.movie.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingHelper(
    private val recyclerView: RecyclerView
) : RecyclerView.OnScrollListener() {

    private val layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager

    /**
     * Property
     */
    private var currentPage: Int = 0
    private var itemPerPage: Int = 10
    private var listener: PagingListener? = null

    /**
     * Method
     */
    fun setItemPerPage(count: Int) {
        this.itemPerPage = count
    }

    fun reset() {
        currentPage = 0
        itemPerPage = 10
    }

    fun setOnLoadMoreListener(listener: PagingListener) {
        this.listener = listener
    }

    private fun hasNextPage(): Boolean {
        val itemCount = recyclerView.adapter?.itemCount ?: 0
        return itemCount >= (currentPage * itemPerPage)
    }

    /**
     * Paging
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        when (layoutManager) {
            is LinearLayoutManager -> attachPaging(layoutManager)
        }
    }

    private fun attachPaging(layoutManager: LinearLayoutManager) {
        layoutManager.run {
            if (hasNextPage()) {
                val firstVisible = findFirstCompletelyVisibleItemPosition()
                val a = childCount + firstVisible >= itemCount
                val b = firstVisible >= 0
                val c = itemCount <= itemPerPage

                if (a && b && c) listener?.onLoadMore()
            }
        }
    }

    interface PagingListener {
        fun onLoadMore()
    }

}