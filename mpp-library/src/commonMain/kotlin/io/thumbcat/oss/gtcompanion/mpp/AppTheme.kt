package io.thumbcat.oss.gtcompanion.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.factory.DefaultListWidgetViewFactory
import dev.icerock.moko.widgets.factory.DefaultListWidgetViewFactoryBase
import dev.icerock.moko.widgets.setListFactory
import dev.icerock.moko.widgets.style.view.MarginValues
import dev.icerock.moko.widgets.style.view.PaddingValues

object AppTheme {
    val baseTheme = Theme {
        setListFactory(
            DefaultListWidgetViewFactory(
                DefaultListWidgetViewFactoryBase.Style(
                    padding = PaddingValues(8f),
                    dividerEnabled = true,
                    margins = MarginValues(top = 50f)
                )
            )
        )
    }
}
