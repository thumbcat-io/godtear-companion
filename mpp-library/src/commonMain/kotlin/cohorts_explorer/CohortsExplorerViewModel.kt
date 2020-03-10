@file:Suppress("unused")

package io.thumbcat.oss.gtcompanion.cohorts_explorer

import co.touchlab.stately.ensureNeverFrozen
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.thumbcat.oss.gtcompanion.domain.Version
import io.thumbcat.oss.gtcompanion.domain.usecase.GetCohorts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

typealias CohortsExplorerData = MutableMap<Long, MutableMap<Long, CohortsExplorerCohort>>

data class CohortsExplorerCohort(
    val championKey: String,
    val championName: String,
    val followerKey: String,
    val followerName: String,
    val categoryName: String
)

@Suppress("MemberVisibilityCanBePrivate", "DuplicatedCode")
@ExperimentalCoroutinesApi
class CohortsExplorerViewModel(
    val eventsDispatcher: EventsDispatcher<CohortRowSelectionListener>
) : ViewModel(), KoinComponent {
    private val getCohortsUseCase: GetCohorts by inject()
    private val _cohorts: MutableLiveData<CohortsExplorerData> = MutableLiveData(mutableMapOf())
    val cohorts: LiveData<CohortsExplorerData> = _cohorts

    init {
        ensureNeverFrozen()
        viewModelScope.launch {
            val request = GetCohorts.Request(Version.Latest)
            val response = getCohortsUseCase.get(request)
            val categories = response.cohorts
                .map { it.category }
                .distinct()
                .sortedBy { it.name }
                .mapIndexed { index, cohort -> index to cohort }
            val cohorts = categories
                .map { (index, category) ->
                    index.toLong() to response.cohorts
                        .filter { it.category == category }
                        .sortedBy { it.champion.name }
                        .mapIndexed { cohortIndex, cohort -> cohortIndex.toLong() to cohort.toPresentationModel() }
                        .associate { it }
                        .toMutableMap()
                }
                .associate { it }
                .toMutableMap()
            _cohorts.postValue(cohorts)
        }
    }

    fun onCohortRowSelected(cohort: CohortsExplorerCohort) {
        eventsDispatcher.dispatchEvent { routeToCohortRosterView(cohort) }
    }

    interface CohortRowSelectionListener {
        fun routeToCohortRosterView(cohort: CohortsExplorerCohort)
    }
}

fun GetCohorts.Response.Cohort.toPresentationModel(): CohortsExplorerCohort {
    return CohortsExplorerCohort(
        championKey = champion.key,
        championName = champion.name,
        followerKey = followerUnit.key,
        followerName = followerUnit.name,
        categoryName = category.name
    )
}
