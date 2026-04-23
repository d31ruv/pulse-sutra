package d31ruv.pulse.sutra.feature.chant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import d31ruv.pulse.sutra.core.data.chant.repository.ChantDashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChantViewModel @Inject constructor(
    chantDashboardRepository: ChantDashboardRepository,
) : ViewModel() {

    val dashboardState: StateFlow<ChantDashboardState> =
        chantDashboardRepository.dashboardState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChantDashboardState(),
        )
}
