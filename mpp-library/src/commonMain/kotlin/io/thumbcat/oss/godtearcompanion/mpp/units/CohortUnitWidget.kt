package io.thumbcat.oss.godtearcompanion.mpp.units

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.clickable
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.factory.DefaultLinearWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultLinearWidgetViewFactoryBase
import dev.icerock.moko.widgets.linear
import dev.icerock.moko.widgets.style.background.Orientation
import dev.icerock.moko.widgets.style.view.PaddingValues
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem

class CohortUnitWidget(
    private val theme: Theme
) {
    fun createWidget(data: LiveData<Data>) =
        with(theme) {
            clickable(
                child = linear(
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    factory = DefaultLinearWidgetViewFactory(
                        DefaultLinearWidgetViewFactoryBase.Style(
                            orientation = Orientation.HORIZONTAL,
                            padding = PaddingValues(4f)
                        )
                    )
                ) {
                    +text(
                        size = WidgetSize.Const(
                            SizeSpec.AsParent,
                            SizeSpec.WrapContent
                        ),
                        text = data.map { it.name.desc() as StringDesc }
                    )
                },
                onClick = {
                    data.value.onClick()
                }
            )
        }

    data class Data(
        val key: String,
        val name: String,
        val onClick: () -> Unit
    )

    class TableUnitItem(
        theme: Theme,
        itemId: Long,
        data: Data
    ) : WidgetsTableUnitItem<Data>(itemId, data) {
        private val unitWidget = CohortUnitWidget(theme)

        override val reuseId: String = "CohortUnitItem"

        override fun createWidget(data: LiveData<Data>): UnitItemRoot {
            return unitWidget.createWidget(data).let { UnitItemRoot.from(it) }
        }
    }
}
