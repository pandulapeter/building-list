package com.pandulapeter.buildinglist.feature.list

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

abstract class BaseAdapter<M : BaseAdapter.UiModel, VH : BaseAdapter.ViewHolder<M>>(changePayload: (oldItem: M, newItem: M) -> Any? = { _, _ -> null }) :
    ListAdapter<M, VH>(object : DiffUtil.ItemCallback<M>() {

        override fun areItemsTheSame(oldItem: M, newItem: M) = oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: M, newItem: M) = oldItem == newItem

        override fun getChangePayload(oldItem: M, newItem: M) = changePayload(oldItem, newItem)
    }) {

    private val delegates = mutableListOf<Pair<KClass<out M>, (ViewGroup) -> VH>>()

    protected fun registerDelegate(delegate: Pair<KClass<out M>, (ViewGroup) -> VH>) = delegates.add(delegate)

    override fun getItemViewType(position: Int) = getItem(position)::class.let { itemType -> delegates.indexOfFirst { it.first == itemType } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegates[viewType].second(parent)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    interface UiModel {
        val id: String
    }

    abstract class ViewHolder<M : UiModel>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(model: M)
    }
}