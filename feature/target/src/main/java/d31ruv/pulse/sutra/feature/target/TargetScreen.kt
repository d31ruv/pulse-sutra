package d31ruv.pulse.sutra.feature.target

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

data class TargetState(
    val mantras: List<String> = emptyList(),
    val selectedMantra: String = "",
    val availableTargets: List<Int> = emptyList(),
    val quickTargets: List<Int> = emptyList(),
    val selectedTarget: Int = 0,
) {
    val sliderPosition: Float
        get() = availableTargets.indexOf(selectedTarget).coerceAtLeast(0)
            .toFloat() / availableTargets.lastIndex.coerceAtLeast(1)

    fun selectMantra(mantra: String): TargetState = copy(selectedMantra = mantra)

    fun selectTarget(target: Int): TargetState = copy(selectedTarget = target)

    companion object {
        fun initial(): TargetState = TargetState(
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
fun TargetScreen(onSettingsClick: () -> Unit) {
    var selectedMantra by rememberSaveable {
        mutableStateOf(TargetState.initial().selectedMantra)
    }
    var selectedTarget by rememberSaveable {
        mutableIntStateOf(TargetState.initial().selectedTarget)
    }
    val baseState = remember { TargetState.initial() }
    val screenState = remember(selectedMantra, selectedTarget) {
        baseState.selectMantra(selectedMantra).selectTarget(selectedTarget)
    }

    TargetContent(
        state = screenState,
        onMantraSelected = { selectedMantra = it },
        onTargetSelected = { selectedTarget = it },
        onSettingsClick = onSettingsClick,
    )
}
