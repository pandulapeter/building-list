package com.pandulapeter.buildinglist

import com.pandulapeter.buildinglist.data.repository.Repository
import com.pandulapeter.buildinglist.feature.BuildingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val repositoryModule = module {
    single { Repository() }
}

private val featureModule = module {
    viewModel { BuildingListViewModel(get()) }
}

val modules = listOf(
    repositoryModule,
    featureModule
)