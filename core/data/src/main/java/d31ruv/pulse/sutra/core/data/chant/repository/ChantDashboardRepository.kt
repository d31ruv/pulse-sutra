package d31ruv.pulse.sutra.core.data.chant.repository

import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import kotlinx.coroutines.flow.Flow

interface ChantDashboardRepository {
    val dashboardState: Flow<ChantDashboardState>
}
