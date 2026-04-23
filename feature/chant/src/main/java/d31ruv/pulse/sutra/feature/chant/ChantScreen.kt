package d31ruv.pulse.sutra.feature.chant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Entry-point composable for the Chant destination.
 * Obtains [ChantViewModel] via Hilt and delegates to [ChantContent].
 */
@Composable
fun ChantScreen(
    onSettingsClick: () -> Unit,
    viewModel: ChantViewModel = hiltViewModel(),
) {
    val state by viewModel.dashboardState.collectAsStateWithLifecycle()

    ChantContent(
        state = state,
        onSettingsClick = onSettingsClick,
    )
}
