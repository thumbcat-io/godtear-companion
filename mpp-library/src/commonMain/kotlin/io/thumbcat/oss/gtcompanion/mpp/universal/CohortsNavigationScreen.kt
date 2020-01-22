package io.thumbcat.oss.gtcompanion.mpp.universal

import dev.icerock.moko.widgets.screen.NavigationScreen
import dev.icerock.moko.widgets.screen.ScreenFactory
import kotlin.reflect.KClass

class CohortsNavigationScreen(
    screenFactory: ScreenFactory
) : NavigationScreen<CohortsScreen>(screenFactory), CohortsScreen.Parent, CohortScreen.Parent {
    override val rootScreen: KClass<out CohortsScreen>
        get() = CohortsScreen::class

    override fun routeToCohort(cohortKey: String) {
        routeToScreen(
            CohortScreen::class,
            CohortScreen.Args(cohortKey)
        )
    }

    interface Parent
}
