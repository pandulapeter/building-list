package com.pandulapeter.buildinglist.feature.list

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pandulapeter.buildinglist.BR
import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.databinding.ItemBuildingBinding
import com.pandulapeter.buildinglist.databinding.ItemBuildingsHeaderBinding
import com.pandulapeter.buildinglist.databinding.ItemExpandableHeaderBinding
import com.pandulapeter.buildinglist.databinding.ItemFilterOptionBinding
import com.pandulapeter.buildinglist.databinding.ItemSortingOptionBinding
import com.pandulapeter.buildinglist.util.inflate

sealed class ViewHolder<B : ViewDataBinding, M : UiModel>(private val binding: B) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiModel: M) = binding.run {
        setVariable(BR.uiModel, uiModel)
        executePendingBindings()
    }

    class ExpandableHeader(parent: ViewGroup) : ViewHolder<ItemExpandableHeaderBinding, UiModel.ExpandableHeader>(parent.inflate(R.layout.item_expandable_header))

    class FilterOption(parent: ViewGroup) : ViewHolder<ItemFilterOptionBinding, UiModel.FilterOption>(parent.inflate(R.layout.item_filter_option))

    class SortingOption(parent: ViewGroup) : ViewHolder<ItemSortingOptionBinding, UiModel.SortingOption>(parent.inflate(R.layout.item_sorting_option))

    class BuildingsHeader(parent: ViewGroup) : ViewHolder<ItemBuildingsHeaderBinding, UiModel.BuildingsHeader>(parent.inflate(R.layout.item_buildings_header))

    class Building(parent: ViewGroup) : ViewHolder<ItemBuildingBinding, UiModel.Building>(parent.inflate(R.layout.item_building))
}