package io.thumbcat.oss.gtcompanion.mpp.universal

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.NavigationItem
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.getArgument
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize

class CohortScreen(
    private val theme: Theme
) : WidgetScreen<Args.Parcel<CohortScreen.Args>>(), NavigationItem {
    override val navigationTitle: StringDesc
        get() = getArgument().cohortKey.desc()

    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {
        TODO("not implemented")
    }

    @Parcelize
    data class Args(val cohortKey: String) : Parcelable

    interface Parent
}

class CohortViewModel(
    cohortKey: String,
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<CohortViewModel.EventsListener> {
    val title: LiveData<StringDesc> = MutableLiveData(initialValue = cohortKey.desc())

    interface EventsListener
}
