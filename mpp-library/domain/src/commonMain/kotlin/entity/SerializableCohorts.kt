package io.thumbcat.oss.gtcompanion.mpp.domain.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
internal data class SerializableCohort(
    val champion: SerializableChampion,
    val follower: SerializableFollower,
    val category: SerializableCategory
) {
    @Serializable
    data class SerializableCategory(
        val key: String,
        val name: String
    )
}

@Serializable
internal data class SerializableFollower(
    val key: String,
    val name: String,
    val count: Int,
    val size: SerializableSize
) {
    @Serializable
    enum class SerializableSize {
        small, large
    }
}

@Serializable
internal data class SerializableChampion(
    val key: String,
    val name: String
)

internal fun SerializableCohort.toDomain(): Cohort
        = Cohort(champion = champion.toDomain(), follower = follower.toDomain(), category = category.toDomain())

internal fun Cohort.toSerializable(): SerializableCohort
        = SerializableCohort(champion = champion.toSerializable(), follower = follower.toSerializable(), category = category.toSerializable())

internal fun SerializableChampion.toDomain(): Champion
        = Champion(key = key, name = name)

internal fun Champion.toSerializable(): SerializableChampion
        = SerializableChampion(key = key, name = name)

internal fun SerializableFollower.toDomain(): Follower
        = Follower(key = key, name = name, count = count, size = size.toDomain())

internal fun Follower.toSerializable(): SerializableFollower
        = SerializableFollower(key = key, name = name, count = count, size = size.toSerializable())

internal fun SerializableCohort.SerializableCategory.toDomain(): Cohort.Category
        = Cohort.Category(key = key, name = name)

internal fun Cohort.Category.toSerializable(): SerializableCohort.SerializableCategory
        = SerializableCohort.SerializableCategory(key = key, name = name)

internal fun SerializableFollower.SerializableSize.toDomain(): Follower.Size
        = Follower.Size.valueOf(name.toUpperCase())

internal fun Follower.Size.toSerializable(): SerializableFollower.SerializableSize
        = SerializableFollower.SerializableSize.valueOf(name.toLowerCase())

internal fun Cohort.toJson(json: Json): String = json.stringify(SerializableCohort.serializer(), toSerializable())