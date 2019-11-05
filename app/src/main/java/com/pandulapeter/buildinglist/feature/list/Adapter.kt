package com.pandulapeter.buildinglist.feature.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class Adapter(
    private val onExpandableHeaderClicked: (position: Int) -> Unit,
    private val onFilterOptionClicked: (countryId: String) -> Unit,
    private val onSortingOptionClicked: (sortingOptionId: String) -> Unit
) : ListAdapter<UiModel, ViewHolder<*, *>>(object : DiffUtil.ItemCallback<UiModel>() {

    override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem == newItem

}) {
    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is UiModel.ExpandableHeader -> TYPE_EXPANDABLE_HEADER
        is UiModel.FilterOption -> TYPE_FILTER_OPTION
        is UiModel.SortingOption -> TYPE_SORTING_OPTION
        is UiModel.BuildingsHeader -> TYPE_BUILDINGS_HEADER
        is UiModel.Building -> TYPE_BUILDING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_EXPANDABLE_HEADER -> ViewHolder.ExpandableHeader(parent) { position -> onExpandableHeaderClicked(position) }
        TYPE_FILTER_OPTION -> ViewHolder.FilterOption(parent) { position -> onFilterOptionClicked((getItem(position) as UiModel.FilterOption).id) }
        TYPE_SORTING_OPTION -> ViewHolder.SortingOption(parent) { position -> onSortingOptionClicked((getItem(position) as UiModel.SortingOption).id) }
        TYPE_BUILDINGS_HEADER -> ViewHolder.BuildingsHeader(parent)
        TYPE_BUILDING -> ViewHolder.Building(parent)
        else -> throw IllegalArgumentException("Unsupported view type: $viewType")
    }

    override fun onBindViewHolder(holder: ViewHolder<*, *>, position: Int) = when (holder) {
        is ViewHolder.ExpandableHeader -> holder.bind(getItem(position) as UiModel.ExpandableHeader)
        is ViewHolder.FilterOption -> holder.bind(getItem(position) as UiModel.FilterOption)
        is ViewHolder.SortingOption -> holder.bind(getItem(position) as UiModel.SortingOption)
        is ViewHolder.BuildingsHeader -> holder.bind(getItem(position) as UiModel.BuildingsHeader)
        is ViewHolder.Building -> holder.bind(getItem(position) as UiModel.Building)
    }

    companion object {
        private const val TYPE_EXPANDABLE_HEADER = 0
        private const val TYPE_FILTER_OPTION = 1
        private const val TYPE_SORTING_OPTION = 2
        private const val TYPE_BUILDINGS_HEADER = 3
        private const val TYPE_BUILDING = 4
    }
}