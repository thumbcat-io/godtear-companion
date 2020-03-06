package io.thumbcat.oss.gtcompanion.domain.entity

sealed class ModelSize {
    abstract val key: String
    abstract val name: String // by putting this here, name can be localized

    data class Small(
        override val key: String,
        override val name: String
    ) : ModelSize()

    data class Large(
        override val key: String,
        override val name: String
    ) : ModelSize()
}
