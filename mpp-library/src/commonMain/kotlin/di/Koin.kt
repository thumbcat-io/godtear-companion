package io.thumbcat.oss.gtcompanion.di

import io.thumbcat.oss.gtcompanion.app.GetCohortsFromRepository
import io.thumbcat.oss.gtcompanion.domain.gateway.CohortsRepository
import io.thumbcat.oss.gtcompanion.persistence.HardCodedJsonCohortsRepository
import io.thumbcat.oss.gtcompanion.domain.usecase.GetCohorts
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@UnstableDefault
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule, coreModule)
}

private const val COHORTS_GATEWAY_IMPL = "hardCodedJsonCohortsRepository"

@UnstableDefault
val coreModule = module {
    single<GetCohorts> {
        GetCohortsFromRepository(cohortsRepository = get(named(COHORTS_GATEWAY_IMPL)))
    }
    single<CohortsRepository>(named(COHORTS_GATEWAY_IMPL)) {
        HardCodedJsonCohortsRepository(json = get(named("strictJson")))
    }
    single(named("nonstrictJson")) {
        Json(JsonConfiguration.Stable.copy(strictMode = false, useArrayPolymorphism = true))
    }
    single(named("strictJson")) {
        Json(JsonConfiguration.Stable.copy(strictMode = true, useArrayPolymorphism = true))
    }
}

expect val platformModule: Module
