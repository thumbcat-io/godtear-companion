package io.thumbcat.oss.gtcompanion.domain

import java.nio.charset.StandardCharsets

actual fun String.isAscii(): Boolean = StandardCharsets.US_ASCII.newEncoder().canEncode(this)
