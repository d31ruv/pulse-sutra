package d31ruv.pulse.sutra.core.data.chant.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

internal fun <T> Flow<T>.replayCacheOrFirst(): T = runBlocking { first() }
