package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MantraSelectionCard(
    modifier: Modifier = Modifier,
    selectedMantra: String = "",
    mantras: List<String> = emptyList(),
    onMantraSelected: (String) -> Unit = {},
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.core_ui_choose_mantra),
            style = typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = colorScheme.primary,
        )
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceContainer),
        ) {
            Spacer(Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier.animateContentSize(),
                    textAlign = TextAlign.Center,
                    text = selectedMantra,
                    style = typography.titleLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    color = colorScheme.onSurface,
                )
            }
            Spacer(Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                mantras.forEach { mantra ->
                    val selected = mantra == selectedMantra
                    val containerColor by animateColorAsState(
                        if (selected) colorScheme.primary else colorScheme.surfaceVariant
                    )
                    val labelColor by animateColorAsState(
                        if (selected) colorScheme.surface else colorScheme.onSurface
                    )
                    AssistChip(
                        onClick = { onMantraSelected(mantra) },
                        label = {
                            Text(text = mantra, style = typography.labelMedium)
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = containerColor,
                            labelColor = labelColor,
                        ),
                        shape = CircleShape,
                        border = null,
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
private fun MantraSelectionCardPreview() {
    PulseSutraTheme {
        MantraSelectionCard()
    }
}
