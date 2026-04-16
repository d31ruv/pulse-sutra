package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.feature.target.TargetDashboardState
import kotlin.math.roundToInt

@Composable
internal fun TargetSelectionCard(
    state: TargetDashboardState,
    onTargetSelected: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            SectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.feature.target.R.string.feature_target_repetition_target))
            Text(
                text = state.selectedTarget.toString(),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(horizontal = 24.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            TargetSlider(
                state = state,
                onTargetSelected = onTargetSelected,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                state.quickTargets.forEach { target ->
                    QuickTargetChip(
                        target = target,
                        selected = state.selectedTarget == target,
                        modifier = Modifier.weight(1f),
                        onClick = { onTargetSelected(target) },
                    )
                }
            }
        }
    }
}

@Composable
internal fun TargetSlider(
    state: TargetDashboardState,
    onTargetSelected: (Int) -> Unit,
) {
    var sliderValue by remember(state.selectedTarget) { mutableFloatStateOf(state.sliderPosition) }
    val colorScheme = MaterialTheme.colorScheme
    val trackGradient = remember(colorScheme) {
        Brush.horizontalGradient(listOf(colorScheme.primary, colorScheme.secondary))
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .align(Alignment.Center),
        ) {
            val strokeHeight = 8.dp.toPx()
            val y = size.height / 2f - strokeHeight / 2f
            drawRoundRect(
                color = colorScheme.surfaceVariant,
                topLeft = Offset(0f, y),
                size = Size(size.width, strokeHeight),
                cornerRadius = CornerRadius(strokeHeight, strokeHeight),
            )
            drawRoundRect(
                brush = trackGradient,
                topLeft = Offset(0f, y),
                size = Size(size.width * sliderValue, strokeHeight),
                cornerRadius = CornerRadius(strokeHeight, strokeHeight),
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
                val index = (value * state.availableTargets.lastIndex).roundToInt()
                    .coerceIn(0, state.availableTargets.lastIndex)
                onTargetSelected(state.availableTargets[index])
            },
            valueRange = 0f..1f,
            steps = state.availableTargets.lastIndex - 1,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.onPrimary,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
        )
    }
}

@Composable
internal fun QuickTargetChip(
    target: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    val chipModifier = if (selected) {
        modifier.shadow(
            elevation = 10.dp,
            shape = MaterialTheme.shapes.medium,
            spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        )
    } else {
        modifier
    }

    Box(
        modifier = chipModifier
            .clip(MaterialTheme.shapes.medium)
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.medium,
                    ),
            )
        }
        Text(
            text = target.toString(),
            color = textColor,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}
