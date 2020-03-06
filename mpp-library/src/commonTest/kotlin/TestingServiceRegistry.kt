package io.thumbcat.oss.gtcompanion

import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

object TestingServiceRegistry {

    internal fun appStart(settings: Settings) {
        val coreModule = module {
            single { settings }
        }

        startKoin { modules(coreModule) }
    }

    internal fun appEnd() {
        stopKoin()
    }
}
