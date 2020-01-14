package io.thumbcat.oss.godtearcompanion.mpp.universal

import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.screen.BottomNavigationItem
import dev.icerock.moko.widgets.screen.BottomNavigationScreen
import dev.icerock.moko.widgets.screen.ScreenFactory
import io.thumbcat.oss.godtearcompanion.mpp.MR

class RootBottomNavigationScreen(
    screenFactory: ScreenFactory
) : BottomNavigationScreen(screenFactory), CohortsNavigationScreen.Parent {
    override val items: List<BottomNavigationItem>
        get() {
            return listOf(
                BottomNavigationItem(
                    id = 1,
                    title = "Cohorts".desc(),
                    screen = CohortsNavigationScreen::class,
                    icon = MR.images.list_simple_black
                )
            )
        }
}
