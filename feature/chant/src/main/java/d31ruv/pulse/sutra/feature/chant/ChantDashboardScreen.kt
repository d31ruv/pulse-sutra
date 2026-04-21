package d31ruv.pulse.sutra.feature.chant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.feature.chant.components.ChantCounter
import d31ruv.pulse.sutra.feature.chant.components.ChantTopBar
import d31ruv.pulse.sutra.feature.chant.components.PrimaryActionButton
import d31ruv.pulse.sutra.feature.chant.components.SecondaryActionRow

/**
 * Entry-point composable for the Chant destination.
 * Obtains [ChantViewModel] via Hilt and delegates to [ChantDashboardContent].
 */
@Composable
fun ChantDashboardScreen(
    onSettingsClick: () -> Unit,
    viewModel: ChantViewModel = hiltViewModel(),
) {
    val state by viewModel.dashboardState.collectAsStateWithLifecycle()
    val resolvedState = state ?: return
    ChantDashboardContent(
        state = resolvedState,
        onSettingsClick = onSettingsClick,
    )
}

@Composable
internal fun ChantDashboardContent(
    state: ChantDashboardState,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val screenWidth = LocalWindowInfo.current.containerDpSize.width
    val maxWidth = when {
        screenWidth < 600.dp -> screenWidth
        screenWidth < 840.dp -> 600.dp
        else -> 840.dp
    }

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = { ChantTopBar(onSettingsClick = onSettingsClick) },
    ) { innerPadding ->
        Box(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.widthIn(max = maxWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    text = stringResource(R.string.core_ui_current_mantra),
                    color = colorScheme.primary,
                    style = typography.labelMedium,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = state.mantraName,
                    color = colorScheme.onSurface,
                    style = typography.displaySmall,
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
    }
}
