package com.pandulapeter.buildinglist.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayDeque

abstract class ImprovedListAdapter<T : ImprovedListAdapter.Item, VH : RecyclerView.ViewHolder>(private val scope: CoroutineScope) : RecyclerView.Adapter<VH>() {

    private var items = emptyList<T>()
    private var job: Job? = null
    private val pendingUpdates = ArrayDeque<List<T>>()

    final override fun getItemCount() = items.size

    fun getItem(position: Int) = items[position]

    fun getCurrentList() = items

    fun submitList(newItems: List<T>, onListUpdated: (() -> Unit)? = null) {
        pendingUpdates.add(newItems)
        if (pendingUpdates.size == 1) {
            update(newItems, onListUpdated)
        }
    }

    private fun update(newItems: List<T>, onListUpdated: (() -> Unit)?) {
        job?.cancel()
        job = scope.launch(Dispatchers.IO) {
            val result = DiffUtil.calculateDiff(DiffCallback(items, newItems))
            job = launch(Dispatchers.Main) {
                items = newItems
                result.dispatchUpdatesTo(this@ImprovedListAdapter)
                processQueue(onListUpdated)
                job = null
            }
        }
    }

    private fun processQueue(onListUpdated: (() -> Unit)?) {
        pendingUpdates.remove()
        if (pendingUpdates.isEmpty()) {
            onListUpdated?.invoke()
        } else {
            if (pendingUpdates.size > 1) {
                val lastList = pendingUpdates.peekLast()
                pendingUpdates.clear()
                pendingUpdates.add(lastList)
            }
            pendingUpdates.peek()?.let { update(it, onListUpdated) }
        }
    }

    interface Item {

        val id: String

        override fun equals(other: Any?): Boolean

        override fun hashCode(): Int
    }

    private class DiffCallback<T : Item>(private val oldItems: List<T>, private val newItems: List<T>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItems[oldItemPosition].id == newItems[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) = Unit
    }
}