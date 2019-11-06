package com.pandulapeter.buildinglist.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.databinding.ActivityBuildingListBinding
import com.pandulapeter.buildinglist.util.Adapter
import com.pandulapeter.buildinglist.feature.list.UiModel
import com.pandulapeter.buildinglist.feature.list.ViewHolder
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
                val maxSpan = SPAN_FULL
                layoutManager = GridLayoutManager(this@BuildingListActivity, maxSpan).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int) = if (viewModel.isItemFullWidth(position)) SPAN_ONE else maxSpan
                    }
                }

                val buildingAdapter = Adapter<UiModel, ViewHolder<*, *>>(
                    delegates = listOf(
                        UiModel.ExpandableHeader::class to { parent -> ViewHolder.ExpandableHeader(parent) { viewModel.onExpandableHeaderClicked(it.id) } },
                        UiModel.FilterOption::class to { parent -> ViewHolder.FilterOption(parent, viewModel::onFilterOptionClicked) },
                        UiModel.SortingOption::class to { parent -> ViewHolder.SortingOption(parent, viewModel::onSortingOptionClicked) },
                        UiModel.BuildingsHeader::class to { parent -> ViewHolder.BuildingsHeader(parent) },
                        UiModel.Building::class to { parent -> ViewHolder.Building(parent) }
                    ),
                    changePayload = { oldItem, newItem ->
                        when {
                            oldItem is UiModel.FilterOption && newItem is UiModel.FilterOption -> Unit
                            else -> null
                        }
                    }
                )

                viewModel.items.observe(this@BuildingListActivity, Observer<List<UiModel>> { items -> buildingAdapter.submitList(items) })
                adapter = buildingAdapter
                setHasFixedSize(true)
            }
        }
    }

    companion object {
        private const val SPAN_FULL = 2
        private const val SPAN_ONE = 1
    }
}