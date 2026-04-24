package d31ruv.pulse.sutra.feature.target

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class TargetDashboardState(
    val mantras: List<String> = emptyList(),
    val selectedMantra: String = "",
    val availableTargets: List<Int> = emptyList(),
    val quickTargets: List<Int> = emptyList(),
    val selectedTarget: Int = 0,
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
    onSettingsClick: () -> Unit,
) {
    var selectedMantra by rememberSaveable { mutableStateOf(TargetDashboardState.initial().selectedMantra) }
    var selectedTarget by rememberSaveable { mutableIntStateOf(TargetDashboardState.initial().selectedTarget) }
    val baseState = remember { TargetDashboardState.initial() }
    val screenState = remember(selectedMantra, selectedTarget) {
        baseState
            .selectMantra(selectedMantra)
            .selectTarget(selectedTarget)
    }

    TargetContent(
        state = screenState,
        onMantraSelected = { selectedMantra = it },
        onTargetSelected = { selectedTarget = it },
        onSettingsClick = onSettingsClick,
    )
}
