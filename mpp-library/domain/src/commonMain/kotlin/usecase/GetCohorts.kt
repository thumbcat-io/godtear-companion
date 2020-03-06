package io.thumbcat.oss.gtcompanion.domain.usecase

import io.thumbcat.oss.gtcompanion.domain.Version

interface GetCohorts {

    suspend fun get(request: Request): Response

    data class Request(val version: Version)

    data class Response(val cohorts: List<Cohort>) {

        data class Cohort(
            val champion: Champion,
            val followerUnit: FollowerUnit,
            val category: CohortCategory
        )

        data class Champion(
            val key: String,
            val name: String
        )

        data class FollowerUnit(
            val key: String,
            val name: String
        )

        data class CohortCategory(
            val key: String,
            val name: String
        )
    }
}
