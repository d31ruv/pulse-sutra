package d31ruv.pulse.sutra.feature.journal.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.feature.journal.SessionHistoryEntry

@Composable
internal fun HistorySection(
    modifier: Modifier = Modifier,
    entries: List<SessionHistoryEntry>,
) {
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.core_ui_history),
            style = typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = colorScheme.primary,
        )
        entries.forEach { entry ->
            SessionHistoryItem(entry = entry)
        }
    }
}
