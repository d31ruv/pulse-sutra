package d31ruv.pulse.sutra.feature.target

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.feature.target.components.BeginSessionButton
import d31ruv.pulse.sutra.feature.target.components.MantraSelectionCard
import d31ruv.pulse.sutra.feature.target.components.RitualAnchorCard
import d31ruv.pulse.sutra.feature.target.components.TargetSelectionCard


data class TargetDashboardState(
    val mantras: List<String>,
    val selectedMantra: String,
    val availableTargets: List<Int>,
    val quickTargets: List<Int>,
    val selectedTarget: Int,
) {
    val sliderPosition: Float
        get() = availableTargets.indexOf(selectedTarget).coerceAtLeast(0).toFloat() /
            availableTargets.lastIndex.coerceAtLeast(1)

    fun selectMantra(mantra: String): TargetDashboardState =
        copy(selectedMantra = mantra)

    fun selectTarget(target: Int): TargetDashboardState =
        copy(selectedTarget = target)

    companion object {
        fun initial(): TargetDashboardState =
            TargetDashboardState(
                mantras = listOf(
                    "Om Mani Padme Hum",
                    "So Hum",
                    "Om Namah Shivaya",
                    "Gayatri Mantra",
                ),
                selectedMantra = "Om Mani Padme Hum",
                availableTargets = listOf(27, 54, 108, 216, 324, 540, 1008),
                quickTargets = listOf(27, 54, 108, 1008),
                selectedTarget = 108,
            )
    }
}

@Composable
fun TargetDashboardScreen(
    modifier: Modifier = Modifier,
) {
    var selectedMantra by rememberSaveable { mutableStateOf(TargetDashboardState.initial().selectedMantra) }
    var selectedTarget by rememberSaveable { mutableStateOf(TargetDashboardState.initial().selectedTarget) }
    val baseState = remember { TargetDashboardState.initial() }
    val screenState = remember(selectedMantra, selectedTarget) {
        baseState
            .selectMantra(selectedMantra)
            .selectTarget(selectedTarget)
    }

    TargetDashboardContent(
        state = screenState,
        onMantraSelected = { selectedMantra = it },
        onTargetSelected = { selectedTarget = it },
        modifier = modifier,
    )
}

@Composable
internal fun TargetDashboardContent(
    state: TargetDashboardState,
    onMantraSelected: (String) -> Unit,
    onTargetSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 672.dp)
                .padding(top = 28.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.feature.target.R.string.feature_target_set_intention),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.feature.target.R.string.feature_target_configure_mantra),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                MantraSelectionCard(
                    state = state,
                    onMantraSelected = onMantraSelected,
                )
                TargetSelectionCard(
                    state = state,
                    onTargetSelected = onTargetSelected,
                )
                RitualAnchorCard()
            }

            BeginSessionButton()
        }
    }
}
