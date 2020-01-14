package io.thumbcat.oss.godtearcompanion.mpp.universal

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.NavigationItem
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.getParentScreen
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize

class CohortsScreen(
    private val theme: Theme,
    private val viewModel: CohortsViewModelContract
) : WidgetScreen<Args.Empty>(), NavigationItem {
    override val navigationTitle: StringDesc = "Cohorts".desc()

    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {
        return with(theme) {
            list(
                size = WidgetSize.AsParent,
                id = Id.List,
                items = viewModel.tableItems
            )
        }
    }

    object Id {
        object List : ListWidget.Id
    }

    private fun onCohortPressed(cohortKey: String) {
        println("$cohortKey pressed")
        getParentScreen<Parent>().routeToCohort(cohortKey)
    }

    interface Parent {
        fun routeToCohort(cohortKey: String)
    }
}

interface CohortsViewModelContract {
    val tableItems: LiveData<List<TableUnitItem>>
}

class CohortsViewModel(
    private val unitsFactory: UnitsFactory
) : ViewModel(), CohortsViewModelContract {
    private val _items: MutableLiveData<List<Pair<String, String>>> = MutableLiveData(
        initialValue = listOf(
            "morrigan" to "Morrigan",
            "nia" to "Nia"
        )
    )
    override val tableItems: LiveData<List<TableUnitItem>> = _items.map { items ->
        items.map { (key, name) ->
            val id = key.hashCode().toLong()
            unitsFactory.createCohortTableUnit(
                itemId = id,
                key = key,
                name = name
            ) {
                println("selected $key cohort")
            }
        }
    }

    interface UnitsFactory {
        fun createCohortTableUnit(
            itemId: Long,
            key: String,
            name: String,
            onClick: () -> Unit
        ): TableUnitItem
    }
}
