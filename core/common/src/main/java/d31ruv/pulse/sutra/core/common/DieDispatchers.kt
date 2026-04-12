package d31ruv.pulse.sutra.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dieDispatcher: DieDispatchers)

enum class DieDispatchers { Default, IO }
