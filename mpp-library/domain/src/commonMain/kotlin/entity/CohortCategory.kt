package io.thumbcat.oss.gtcompanion.domain.entity

sealed class CohortCategory {
    abstract val key: String
    abstract val name: String // by putting this here, name can be localized

    data class Maelstrom(
        override val key: String,
        override val name: String
    ) : CohortCategory()

    data class Shaper(
        override val key: String,
        override val name: String
    ) : CohortCategory()

    data class Guardian(
        override val key: String,
        override val name: String
    ) : CohortCategory()

    data class Slayer(
        override val key: String,
        override val name: String
    ) : CohortCategory()
}
