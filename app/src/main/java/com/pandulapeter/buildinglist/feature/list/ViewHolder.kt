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
import com.pandulapeter.buildinglist.util.Adapter
import com.pandulapeter.buildinglist.util.inflate

sealed class ViewHolder<B : ViewDataBinding, M : UiModel>(protected val binding: B) : Adapter.ViewHolder<UiModel>(binding.root) {

    override fun bind(model: UiModel) = binding.run {
        setVariable(BR.uiModel, model)
        executePendingBindings()
    }

    class ExpandableHeader(
        parent: ViewGroup,
        onItemClicked: (position: UiModel.ExpandableHeader) -> Unit
    ) : ViewHolder<ItemExpandableHeaderBinding, UiModel.ExpandableHeader>(parent.inflate(R.layout.item_expandable_header)) {

        init {
            binding.root.setOnClickListener {
                binding.uiModel?.also { uiModel ->
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemClicked(uiModel)
                    }
                }
            }
        }
    }

    class FilterOption(
        parent: ViewGroup,
        onItemClicked: (filterOptionId: String) -> Unit
    ) : ViewHolder<ItemFilterOptionBinding, UiModel.FilterOption>(parent.inflate(R.layout.item_filter_option)) {

        init {
            binding.checkbox.setOnCheckedChangeListener { _, newValue ->
                binding.uiModel?.also { uiModel ->
                    if (uiModel.isChecked != newValue) {
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            onItemClicked(uiModel.id)
                        }
                    }
                }
            }
        }
    }

    class SortingOption(
        parent: ViewGroup,
        onItemClicked: (sortingOptionId: String) -> Unit
    ) : ViewHolder<ItemSortingOptionBinding, UiModel.SortingOption>(parent.inflate(R.layout.item_sorting_option)) {

        init {
            binding.radioButton.setOnCheckedChangeListener { _, newValue ->
                binding.uiModel?.also { uiModel ->
                    if (uiModel.isChecked != newValue) {
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            onItemClicked(uiModel.id)
                        }
                    }
                }
            }
        }
    }

    class BuildingsHeader(
        parent: ViewGroup
    ) : ViewHolder<ItemBuildingsHeaderBinding, UiModel.BuildingsHeader>(parent.inflate(R.layout.item_buildings_header))

    class Building(
        parent: ViewGroup
    ) : ViewHolder<ItemBuildingBinding, UiModel.Building>(parent.inflate(R.layout.item_building))
}