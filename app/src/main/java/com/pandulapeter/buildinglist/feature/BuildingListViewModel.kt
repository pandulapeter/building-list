package com.pandulapeter.buildinglist.feature

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.data.model.Country
import com.pandulapeter.buildinglist.data.repository.Repository
import com.pandulapeter.buildinglist.feature.list.UiModel
import java.util.UUID
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BuildingListViewModel(
    private val repository: Repository
) : ViewModel() {

    val items: LiveData<List<UiModel>> get() = _items
    private val _items = MutableLiveData<List<UiModel>>()

    private var isFilterByCountryExpanded by RefreshingProperty(true)
    private val selectedCountryIds by RefreshingProperty(emptyList<String>())
    private var isSortByExpanded by RefreshingProperty(true)
    private var selectedSortingMode: SortingMode by RefreshingProperty(SortingMode.CONSTRUCTION_YEAR)

    init {
        refreshItems()
    }

    private fun refreshItems() {
        _items.value = mutableListOf<UiModel>().apply {
            add(
                UiModel.ExpandableHeader(
                    id = ID_FILTER_BY_COUNTRY,
                    titleResourceId = R.string.filter_by_country,
                    isExpanded = isFilterByCountryExpanded
                )
            )
            addAll(Country.values().map { country ->
                UiModel.FilterOption(
                    id = country.id,
                    titleResourceId = country.nameResourceId,
                    isChecked = selectedCountryIds.contains(country.id),
                    icon = country.flagDrawableResourceId
                )
            })
            add(
                UiModel.ExpandableHeader(
                    id = ID_SORT_BY,
                    titleResourceId = R.string.sort_by,
                    isExpanded = isSortByExpanded
                )
            )
            addAll(SortingMode.values().map { sortByMode ->
                UiModel.SortingOption(
                    id = sortByMode.id,
                    titleResourceId = sortByMode.nameResourceId,
                    isChecked = sortByMode == selectedSortingMode
                )
            })

            val buildings = repository.buildings
                .filter { selectedCountryIds.contains(it.country.id) }
                .sortedBy {
                    when (selectedSortingMode) {
                        SortingMode.CONSTRUCTION_YEAR -> it.constructionYear
                        SortingMode.HEIGHT -> it.height
                    }
                }

            add(
                UiModel.BuildingsHeader(
                    id = ID_BUILDINGS_HEADER,
                    buildingCount = buildings.size
                )
            )
            addAll(buildings.map { building ->
                UiModel.Building(
                    id = building.id,
                    name = building.name,
                    thumbnailDrawableResourceId = building.thumbnailDrawableResourceId
                )
            })
        }
    }

    private enum class SortingMode(
        val id: String = UUID.randomUUID().toString(),
        @StringRes val nameResourceId: Int
    ) {
        CONSTRUCTION_YEAR(nameResourceId = R.string.construction_year),
        HEIGHT(nameResourceId = R.string.height)
    }

    private class RefreshingProperty<T>(private var value: T) : ReadWriteProperty<BuildingListViewModel, T> {

        override fun getValue(thisRef: BuildingListViewModel, property: KProperty<*>) = value

        override fun setValue(thisRef: BuildingListViewModel, property: KProperty<*>, value: T) {
            this.value = value
            thisRef.refreshItems()
        }
    }

    companion object {
        private const val ID_FILTER_BY_COUNTRY = "filterByCountry"
        private const val ID_SORT_BY = "sortBy"
        private const val ID_BUILDINGS_HEADER = "buildingsHeader"
    }
}