package io.thumbcat.oss.gtcompanion.mpp.domain.entity

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize

@Parcelize
data class Cohort(
    val champion: Champion,
    val follower: Follower,
    val category: Category
) : Parcelable {

    @Parcelize
    data class Category(
        val key: String,
        val name: String
    ) : Parcelable
}

@Parcelize
data class Follower(
    val key: String,
    val name: String,
    val count: Int,
    val size: Size
) : Parcelable {

    @Parcelize
    enum class Size : Parcelable {
        SMALL, LARGE
    }
}

@Parcelize
data class Champion(
    val key: String,
    val name: String
) : Parcelable