package com.pandulapeter.buildinglist.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.databinding.ActivityBuildingListBinding
import com.pandulapeter.buildinglist.feature.list.Adapter
import com.pandulapeter.buildinglist.feature.list.UiModel
import org.koin.android.ext.android.inject

class BuildingListActivity : AppCompatActivity() {

    private val viewModel by inject<BuildingListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityBuildingListBinding>(this, R.layout.activity_building_list).also { binding ->
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@BuildingListActivity)
                val buildingAdapter = Adapter()
                viewModel.items.observe(this@BuildingListActivity, Observer<List<UiModel>> { items -> buildingAdapter.submitList(items) })
                adapter = buildingAdapter
                setHasFixedSize(true)
            }
        }
    }
}