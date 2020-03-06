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

typealias CohortsExplorerData = MutableMap<Long, MutableMap<Long, CohortsExplorerRowData>>

data class CohortsExplorerRowData(
    val championKey: String,
    val championName: String,
    val followerKey: String,
    val followerName: String,
    val categoryName: String
)

@ExperimentalCoroutinesApi
class CohortsExplorerViewModel(
    val eventsDispatcher: EventsDispatcher<CohortRowSelectionListener>,
    private val viewUpdate: (LiveData<CohortsExplorerData>) -> Unit
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
            // for android
            _cohorts.value = cohorts
            /* for iOS
             * MUST be MutableMap. Obj-C doesn't have an implementation for a read-only Map.
             * MUST be Long otherwise Swift will need to cast to Int32 as 64-bit integers are the default.
             */
            viewUpdate(this@CohortsExplorerViewModel.cohorts)
        }
    }

    fun onCohortRowSelected(cohort: CohortsExplorerRowData) {
        eventsDispatcher.dispatchEvent { routeToWarbandView(cohort) }
    }

    interface CohortRowSelectionListener {
        fun routeToWarbandView(cohort: CohortsExplorerRowData)
    }
}

fun GetCohorts.Response.Cohort.toPresentationModel(): CohortsExplorerRowData {
    return CohortsExplorerRowData(
        championKey = champion.key,
        championName = champion.name,
        followerKey = followerUnit.key,
        followerName = followerUnit.name,
        categoryName = category.name
    )
}
