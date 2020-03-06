package io.thumbcat.oss.gtcompanion.di

import com.russhwolf.settings.AndroidSettings
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { AndroidSettings.Factory(get()).create("GTCOMPANION_SETTINGS") }
}
