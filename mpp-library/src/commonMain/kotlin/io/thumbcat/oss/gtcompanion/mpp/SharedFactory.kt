package io.thumbcat.oss.gtcompanion.mpp

import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.core.Theme
import io.thumbcat.oss.gtcompanion.mpp.units.CohortUnitWidget
import io.thumbcat.oss.gtcompanion.mpp.universal.CohortsViewModel

class SharedFactory {
    val mainWidgetScope by lazy {
        Theme()
    }

    val cohortsUnitsFactory by lazy {
        object : CohortsViewModel.UnitsFactory {
            override fun createCohortTableUnit(
                itemId: Long,
                key: String,
                name: String,
                onClick: () -> Unit
            ): TableUnitItem = CohortUnitWidget.TableUnitItem(
                theme = mainWidgetScope,
                itemId = itemId,
                data = CohortUnitWidget.Data(
                    key = key,
                    name = name,
                    onClick = onClick
                )
            )
        }
    }
}
