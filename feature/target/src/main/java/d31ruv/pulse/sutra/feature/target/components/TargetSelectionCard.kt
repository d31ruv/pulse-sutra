package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme
import d31ruv.pulse.sutra.feature.target.TargetState
import kotlin.math.roundToInt

@Composable
internal fun TargetSelectionCard(
    state: TargetState,
    onTargetSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shapes = MaterialTheme.shapes
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    var sliderValue by remember(state.selectedTarget) {
        mutableFloatStateOf(state.sliderPosition)
    }
    val animatedSelectedTarget by animateIntAsState(targetValue = state.selectedTarget)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = stringResource(R.string.core_ui_repetition_target),
                style = typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary,
            )
            Text(
                text = "$animatedSelectedTarget",
                style = typography.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
                color = colorScheme.primary,
            )
        }
        Spacer(Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceContainer),
        ) {
            Slider(
                modifier = Modifier.padding(32.dp),
                value = sliderValue,
                onValueChange = { value ->
                    sliderValue = value
                    val index = (value * state.availableTargets.lastIndex).roundToInt()
                        .coerceIn(0, state.availableTargets.lastIndex)
                    onTargetSelected(state.availableTargets[index])
                },
                valueRange = 0f..1f,
                steps = state.availableTargets.lastIndex - 1,
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                state.quickTargets.forEach { target ->
                    val selected = state.selectedTarget == target
                    val containerColor by animateColorAsState(
                        targetValue = if (selected) colorScheme.primary else colorScheme.surfaceVariant,
                    )
                    val contentColor by animateColorAsState(
                        targetValue = if (selected) colorScheme.surface else colorScheme.onSurface,
                    )
                    val border by animateDpAsState(
                        targetValue = if (selected) 2.dp else 0.dp,
                    )
                    AssistChip(
                        onClick = { onTargetSelected(target) },
                        label = {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "$target",
                                style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = containerColor,
                            labelColor = contentColor,
                        ),
                        shape = shapes.medium,
                        border = BorderStroke(border, colorScheme.surfaceVariant),
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun TargetSelectionCardPreview() {
    PulseSutraTheme {
        TargetSelectionCard(
            state = TargetState(),
            onTargetSelected = {},
        )
    }
}
