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
    private var selectedCountryIds by RefreshingProperty(Country.values().map { it.id })
    private var isSortByExpanded by RefreshingProperty(true)
    private var selectedSortingMode by RefreshingProperty(SortingMode.HEIGHT)

    init {
        refreshItems()
    }

    fun onExpandableHeaderClicked(headerId: String) = when (headerId) {
        ID_FILTER_BY_COUNTRY -> isFilterByCountryExpanded = !isFilterByCountryExpanded
        ID_SORT_BY -> isSortByExpanded = !isSortByExpanded
        else -> throw IllegalArgumentException("Invalid header ID: $headerId")
    }

    fun onFilterOptionClicked(countryId: String) {
        selectedCountryIds = if (selectedCountryIds.contains(countryId)) {
            selectedCountryIds.filterNot { it == countryId }
        } else {
            selectedCountryIds.toMutableList().apply {
                add(countryId)
            }
        }
    }

    fun onSortingOptionClicked(sortingOptionId: String) {
        selectedSortingMode = SortingMode.values().first { it.id == sortingOptionId }
    }

    fun isItemFullWidth(position: Int) = _items.value?.get(position)?.let {
        it is UiModel.FilterOption || it is UiModel.Building
    } == true

    private fun refreshItems() {
        _items.value = mutableListOf<UiModel>().apply {
            addFilterSection()
            addSortingSection()
            addBuildingSection()
        }
    }

    private fun MutableList<UiModel>.addFilterSection() {
        add(
            UiModel.ExpandableHeader(
                id = ID_FILTER_BY_COUNTRY,
                titleResourceId = R.string.filter_by_country,
                isExpanded = isFilterByCountryExpanded
            )
        )
        if (isFilterByCountryExpanded) {
            addAll(Country.values().map { country ->
                UiModel.FilterOption(
                    id = country.id,
                    titleResourceId = country.nameResourceId,
                    isChecked = selectedCountryIds.contains(country.id),
                    iconResourceId = country.flagDrawableResourceId
                )
            })
        }
    }

    private fun MutableList<UiModel>.addSortingSection() {
        add(
            UiModel.ExpandableHeader(
                id = ID_SORT_BY,
                titleResourceId = R.string.sort_by,
                isExpanded = isSortByExpanded
            )
        )
        if (isSortByExpanded) {
            addAll(SortingMode.values().map { sortByMode ->
                UiModel.SortingOption(
                    id = sortByMode.id,
                    titleResourceId = sortByMode.nameResourceId,
                    isChecked = sortByMode == selectedSortingMode
                )
            })
        }
    }

    private fun MutableList<UiModel>.addBuildingSection() {
        val buildings = repository.buildings
            .filter { selectedCountryIds.contains(it.country.id) }
            .sortedBy {
                when (selectedSortingMode) {
                    SortingMode.CONSTRUCTION_YEAR -> it.constructionYear
                    SortingMode.HEIGHT -> -it.height
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
                height = building.height,
                constructionYear = building.constructionYear,
                thumbnailDrawableResourceId = building.thumbnailDrawableResourceId
            )
        })
    }

    private enum class SortingMode(
        val id: String = UUID.randomUUID().toString(),
        @StringRes val nameResourceId: Int
    ) {
        HEIGHT(nameResourceId = R.string.height),
        CONSTRUCTION_YEAR(nameResourceId = R.string.construction_year)
    }

    private class RefreshingProperty<T>(private var value: T) : ReadWriteProperty<BuildingListViewModel, T> {

        override fun getValue(thisRef: BuildingListViewModel, property: KProperty<*>) = value

        override fun setValue(thisRef: BuildingListViewModel, property: KProperty<*>, value: T) {
            if (this.value != value) {
                this.value = value
                thisRef.refreshItems()
            }
        }
    }

    companion object {
        private const val ID_FILTER_BY_COUNTRY = "filterByCountry"
        private const val ID_SORT_BY = "sortBy"
        private const val ID_BUILDINGS_HEADER = "buildingsHeader"
    }
}