package io.thumbcat.oss.gtcompanion

actual fun currentTimeMillis(): Long = System.currentTimeMillis()

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}
