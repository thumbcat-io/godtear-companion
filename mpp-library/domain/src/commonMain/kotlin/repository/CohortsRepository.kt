package io.thumbcat.oss.gtcompanion.mpp.domain.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.thumbcat.oss.gtcompanion.mpp.domain.api.CohortsApi
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.Cohort
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.SerializableCohort
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.toDomain
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.toJson
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

interface CohortsRepository {
    suspend fun fetchCohorts(version: String): List<Cohort>
}

class SettingsCachingCohortsRepository internal constructor(
    private val cohortsApi: CohortsApi,
    private val settings: Settings,
    private val json: Json
) : CohortsRepository {

    private val settingsKeyPrefix: String = "cohorts"
    private fun buildSettingsKey(version: String): String = "$settingsKeyPrefix-$version"

    override suspend fun fetchCohorts(version: String): List<Cohort> {
        return when (val key = buildSettingsKey(version)) {
            in settings -> {
                json.parse(
                    SerializableCohort.serializer().list,
                    settings[key] ?: error("expected a value that was asserted to exist")
                ).map { it.toDomain() }
            }
            else -> {
                val cohorts = cohortsApi.fetchCohorts(version)
                settings[key] = cohorts.joinToString(prefix = "[", postfix = "]") {
                    it.toJson(json)
                }
                cohorts
            }
        }
    }
}