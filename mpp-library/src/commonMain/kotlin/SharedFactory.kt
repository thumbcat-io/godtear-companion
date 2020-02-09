package io.thumbcat.oss.gtcompanion.mpp

import com.russhwolf.settings.Settings
import io.thumbcat.oss.gtcompanion.mpp.domain.di.DomainFactory
import io.thumbcat.oss.gtcompanion.mpp.domain.entity.Cohort

class SharedFactory(
    settings: Settings
) {
    private val domainFactory = DomainFactory(
        settings = settings
    )

    fun cohorts(): List<Cohort> {
//        domainFactory.cohortsRepository.fetchCohorts(version = CURRENT_VERSION)
        return emptyList()
    }
}