package io.thumbcat.oss.gtcompanion.domain

@ExperimentalStdlibApi
actual fun String.isAscii(): Boolean = this.toCharArray().any { c -> c.toInt() !in 0..127 }
