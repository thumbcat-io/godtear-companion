package io.thumbcat.oss.gtcompanion

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext = Dispatchers.Default
