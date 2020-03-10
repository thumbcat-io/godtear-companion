@file:Suppress("unused")

package io.thumbcat.oss.gtcompanion.warband_explorer

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

typealias WarbandExplorerData = MutableMap<Long, MutableMap<Long, WarbandExplorerCohort>>

data class WarbandExplorerCohort(
    val championKey: String,
    val championName: String,
    val followerKey: String,
    val followerName: String,
    val categoryName: String
)

@Suppress("MemberVisibilityCanBePrivate", "DuplicatedCode")
@ExperimentalCoroutinesApi
class WarbandExplorerViewModel(
    val eventsDispatcher: EventsDispatcher<WarbandSelectionListener>
) : ViewModel(), KoinComponent {
    private val getCohortsUseCase: GetCohorts by inject()
    private val _cohorts: MutableLiveData<WarbandExplorerData> = MutableLiveData(mutableMapOf())
    val cohorts: LiveData<WarbandExplorerData> = _cohorts
    val maxSelectedRows: Long = 3

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

    fun onWarbandSelected(cohorts: List<WarbandExplorerCohort>) {
        eventsDispatcher.dispatchEvent { routeToWarbandView(cohorts) }
    }

    interface WarbandSelectionListener {
        fun routeToWarbandView(cohorts: List<WarbandExplorerCohort>)
    }
}

fun GetCohorts.Response.Cohort.toPresentationModel(): WarbandExplorerCohort {
    return WarbandExplorerCohort(
        championKey = champion.key,
        championName = champion.name,
        followerKey = followerUnit.key,
        followerName = followerUnit.name,
        categoryName = category.name
    )
}
