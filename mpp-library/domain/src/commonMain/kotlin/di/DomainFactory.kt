package io.thumbcat.oss.gtcompanion.mpp.domain.di

import com.russhwolf.settings.Settings
import io.thumbcat.oss.gtcompanion.mpp.domain.api.CohortsApi
import io.thumbcat.oss.gtcompanion.mpp.domain.api.HardPackagedJsonCohortsApi
import io.thumbcat.oss.gtcompanion.mpp.domain.repository.CohortsRepository
import io.thumbcat.oss.gtcompanion.mpp.domain.repository.SettingsCachingCohortsRepository
import kotlinx.serialization.json.Json

class DomainFactory(
    private val settings: Settings
) {

    private val json: Json by lazy {
        @Suppress("EXPERIMENTAL_API_USAGE")
        Json.nonstrict
    }

    val cohortsApi: CohortsApi by lazy {
        HardPackagedJsonCohortsApi(json = json)
    }

    val cohortsRepository: CohortsRepository by lazy {
        SettingsCachingCohortsRepository(
            settings = settings,
            cohortsApi = cohortsApi,
            json = json
        )
    }
}