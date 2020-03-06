package io.thumbcat.oss.gtcompanion.di

import com.russhwolf.settings.AppleSettings
import kotlinx.serialization.UnstableDefault
import org.koin.dsl.module

@UnstableDefault
fun initKoin() = initKoin {}
actual val platformModule = module {
    single { AppleSettings.Factory().create("GTCOMPANION_SETTINGS") }
}
