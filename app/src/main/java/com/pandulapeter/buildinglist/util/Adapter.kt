package com.pandulapeter.buildinglist.util

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

class Adapter<M : Adapter.UiModel<*>, VH : Adapter.ViewHolder<M>>(
    private val delegates: List<Pair<KClass<out M>, (ViewGroup) -> VH>>,
    changePayload: (oldItem: M, newItem: M) -> Any? = { _, _ -> null }
) : ListAdapter<M, VH>(object : DiffUtil.ItemCallback<M>() {

    override fun areItemsTheSame(oldItem: M, newItem: M) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: M, newItem: M) = oldItem == newItem

    override fun getChangePayload(oldItem: M, newItem: M) = changePayload(oldItem, newItem)
}) {
    override fun getItemViewType(position: Int) = getItem(position)::class.let { itemType -> delegates.indexOfFirst { it.first == itemType } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegates[viewType].second(parent)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    public override fun getItem(position: Int): M = super.getItem(position)

    interface UiModel<ID: Any> {

        val id: ID

        override fun equals(other: Any?): Boolean
    }

    abstract class ViewHolder<M : UiModel<*>>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(model: M)
    }
}