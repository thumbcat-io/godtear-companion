package io.thumbcat.oss.gtcompanion

expect fun currentTimeMillis(): Long

internal expect fun printThrowable(t: Throwable)
