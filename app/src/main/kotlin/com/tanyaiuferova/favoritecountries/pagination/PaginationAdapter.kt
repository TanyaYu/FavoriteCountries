package com.tanyaiuferova.favoritecountries.pagination

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Tanya Yuferova
 * Date: 10/7/2020
 */
class PaginationAdapter<T, VH : RecyclerView.ViewHolder>(
    private val pageSize: Int,
    private val newPageRequest: () -> Unit,
    private val adapter: ListAdapter<T, VH>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        adapter.registerAdapterDataObserver(Observer(this))
    }

    fun submitList(list: List<T>?) {
        adapter.submitList(list)
    }

    override fun getItemCount(): Int {
        return adapter.itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return adapter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindViewHolder(holder, position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        bindViewHolder(holder, position, payloads)
    }

    @Suppress("UNCHECKED_CAST")
    private fun bindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any> = mutableListOf()
    ) {
        adapter.onBindViewHolder(holder as VH, position, payloads)

        if (position > itemCount - pageSize / 2) {
            newPageRequest()
        }
    }

    private class Observer(private val adapter: RecyclerView.Adapter<*>) : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            adapter.notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            adapter.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            adapter.notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            adapter.notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(positionStart, itemCount, payload)
        }
    }

}