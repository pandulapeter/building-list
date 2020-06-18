package com.pandulapeter.buildinglist.util

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

class Adapter<M : ImprovedListAdapter.Item, VH : Adapter.ViewHolder<M>>(
    scope: CoroutineScope,
    private val delegates: List<Pair<KClass<out M>, (ViewGroup) -> VH>>
) : ImprovedListAdapter<M, VH>(scope) {

    override fun getItemViewType(position: Int) = getItem(position)::class.let { itemType -> delegates.indexOfFirst { it.first == itemType } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegates[viewType].second(parent)

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    abstract class ViewHolder<M : Item>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(model: M)
    }
}