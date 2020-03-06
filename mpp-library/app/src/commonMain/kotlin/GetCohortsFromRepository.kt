package io.thumbcat.oss.gtcompanion.app

import io.thumbcat.oss.gtcompanion.domain.entity.Champion
import io.thumbcat.oss.gtcompanion.domain.entity.Cohort
import io.thumbcat.oss.gtcompanion.domain.entity.CohortCategory
import io.thumbcat.oss.gtcompanion.domain.entity.FollowerUnit
import io.thumbcat.oss.gtcompanion.domain.gateway.CohortsRepository
import io.thumbcat.oss.gtcompanion.domain.usecase.GetCohorts

class GetCohortsFromRepository(
    private val cohortsRepository: CohortsRepository
) : GetCohorts {

    override suspend fun get(request: GetCohorts.Request): GetCohorts.Response {
        val cohorts = cohortsRepository.getAll(version = request.version)
            .map { cohort -> cohort.toOutputBoundary() }
        return GetCohorts.Response(cohorts)
    }
}

private fun Cohort.toOutputBoundary(): GetCohorts.Response.Cohort {
    return GetCohorts.Response.Cohort(
        champion = champion.toOutputBoundary(),
        followerUnit = followerUnit.toOutputBoundary(),
        category = category.toOutputBoundary()
    )
}

private fun Champion.toOutputBoundary(): GetCohorts.Response.Champion {
    return GetCohorts.Response.Champion(
        key = key,
        name = name
    )
}

private fun FollowerUnit.toOutputBoundary(): GetCohorts.Response.FollowerUnit {
    return GetCohorts.Response.FollowerUnit(
        key = key,
        name = name
    )
}

private fun CohortCategory.toOutputBoundary(): GetCohorts.Response.CohortCategory {
    return GetCohorts.Response.CohortCategory(
        key = key,
        name = name
    )
}
