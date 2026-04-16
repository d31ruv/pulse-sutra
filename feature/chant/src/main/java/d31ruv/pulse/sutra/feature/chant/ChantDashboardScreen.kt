package d31ruv.pulse.sutra.feature.chant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import d31ruv.pulse.sutra.feature.chant.components.ChantCounter
import d31ruv.pulse.sutra.feature.chant.components.PrimaryActionButton
import d31ruv.pulse.sutra.feature.chant.components.SecondaryActionRow

/**
 * Entry-point composable for the Chant destination.
 * Obtains [ChantViewModel] via Hilt and delegates to [ChantDashboardContent].
 */
@Composable
fun ChantDashboardScreen(
    viewModel: ChantViewModel = hiltViewModel(),
) {
    val state by viewModel.dashboardState.collectAsStateWithLifecycle()
    val resolvedState = state ?: return
    ChantDashboardContent(state = resolvedState)
}

@Composable
internal fun ChantDashboardContent(
    state: ChantDashboardState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            text = state.mantraLabel,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = state.mantraName,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(14.dp))

        ChantCounter(
            currentCount = state.currentCount,
            targetCount = state.targetCount,
        )

        Spacer(modifier = Modifier.height(36.dp))

        PrimaryActionButton(label = state.primaryActionLabel)

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryActionRow(manualActionLabel = state.manualActionLabel)
    }
}
