import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.Screen
import io.thumbcat.oss.gtcompanion.mpp.AppTheme
import io.thumbcat.oss.gtcompanion.mpp.SharedFactory
import io.thumbcat.oss.gtcompanion.mpp.universal.*
import kotlin.reflect.KClass

class App : BaseApplication() {
    override fun setup() {
        val sharedFactory = SharedFactory()
        val theme = AppTheme.baseTheme

        registerScreenFactory(RootBottomNavigationScreen::class) { RootBottomNavigationScreen(this) }
        registerScreenFactory(CohortsNavigationScreen::class) { CohortsNavigationScreen(this) }
        registerScreenFactory(CohortsScreen::class) { CohortsScreen(theme, CohortsViewModel(sharedFactory.cohortsUnitsFactory)) }
        registerScreenFactory(CohortScreen::class) { CohortScreen(theme) }
    }

    override fun getRootScreen(): KClass<out Screen<Args.Empty>> {
        return RootBottomNavigationScreen::class
    }
}
