package com.pandulapeter.buildinglist.feature.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class UiModel {

    abstract val id: String

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
        @DrawableRes val thumbnailDrawableResourceId: Int
    ) : UiModel()
}