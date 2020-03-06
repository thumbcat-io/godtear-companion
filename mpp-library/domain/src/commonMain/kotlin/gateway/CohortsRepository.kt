package io.thumbcat.oss.gtcompanion.domain.gateway

import io.thumbcat.oss.gtcompanion.domain.Version
import io.thumbcat.oss.gtcompanion.domain.entity.Cohort

interface CohortsRepository {

    /**
     * Retrieve a complete list of versioned [Cohort] entities.
     *
     * @param version The version of [Cohort] entities to retrieve.
     * @return [Cohort] entities.
     */
    suspend fun getAll(version: Version): List<Cohort>

}
