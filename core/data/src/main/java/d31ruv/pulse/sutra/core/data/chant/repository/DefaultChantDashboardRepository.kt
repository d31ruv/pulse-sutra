package d31ruv.pulse.sutra.core.data.chant.repository

import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DefaultChantDashboardRepository @Inject constructor() : ChantDashboardRepository {
    override val dashboardState: Flow<ChantDashboardState> = flowOf(
        ChantDashboardState(
            sanctuaryTitle = "Digital Sanctuary",
            mantraLabel = "CURRENT MANTRA",
            mantraName = "Om Namah Shivaya",
            currentCount = 54,
            targetCount = 108,
        ),
    )
}
