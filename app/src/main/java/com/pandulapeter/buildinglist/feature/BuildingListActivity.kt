package com.pandulapeter.buildinglist.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.databinding.ActivityBuildingListBinding
import org.koin.android.ext.android.inject

class BuildingListActivity : AppCompatActivity() {

    private val viewModel by inject<BuildingListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBuildingListBinding>(this, R.layout.activity_building_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}