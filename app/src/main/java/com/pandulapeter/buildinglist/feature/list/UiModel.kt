package com.pandulapeter.buildinglist.feature.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pandulapeter.buildinglist.util.Adapter

sealed class UiModel : Adapter.UiModel<String> {

    abstract override val id: String

    data class ExpandableHeader(
        override val id: String,
        @StringRes val titleResourceId: Int,
        val isExpanded: Boolean
    ) : UiModel()

    data class FilterOption(
        override val id: String,
        @StringRes val titleResourceId: Int,
        val isChecked: Boolean,
        @DrawableRes val iconResourceId: Int
    ) : UiModel()

    data class SortingOption(
        override val id: String,
        @StringRes val titleResourceId: Int,
        val isChecked: Boolean
    ) : UiModel()

    data class BuildingsHeader(
        override val id: String,
        val buildingCount: Int
    ) : UiModel()

    data class Building(
        override val id: String,
        val name: String,
        val height: Int,
        val constructionYear: Int,
        @DrawableRes val thumbnailDrawableResourceId: Int
    ) : UiModel()
}