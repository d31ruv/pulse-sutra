package d31ruv.diecast.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dieDispatcher: DieDispatchers)

enum class DieDispatchers { Default, IO }