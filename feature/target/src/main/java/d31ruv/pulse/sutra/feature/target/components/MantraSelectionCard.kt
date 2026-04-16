package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.feature.target.TargetDashboardState

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MantraSelectionCard(
    state: TargetDashboardState,
    onMantraSelected: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.feature.target.R.string.feature_target_choose_mantra))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                text = state.selectedMantra,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.mantras
                    .filterNot { it == state.selectedMantra }
                    .forEach { mantra ->
                        AssistChip(
                            label = mantra,
                            selected = false,
                            onClick = { onMantraSelected(mantra) },
                        )
                    }
            }
        }
    }
}

@Composable
internal fun AssistChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val background = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val content = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    Text(
        text = label,
        modifier = Modifier
            .clip(androidx.compose.foundation.shape.CircleShape)
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        color = content,
        style = MaterialTheme.typography.labelMedium,
    )
}
