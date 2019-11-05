package com.pandulapeter.buildinglist.feature

import androidx.lifecycle.ViewModel
import com.pandulapeter.buildinglist.data.repository.BuildingRepository

class BuildingListViewModel(
    private val buildingRepository: BuildingRepository
) : ViewModel()