package d31ruv.pulse.sutra.feature.journal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.feature.journal.components.ContinueJourneyCard
import d31ruv.pulse.sutra.feature.journal.components.HistorySection
import d31ruv.pulse.sutra.feature.journal.components.WeeklyProgressCard

data class SessionHistoryState(
    val weeklyTotal: Int,
    val highlightedDayIndex: Int,
    val chartHeights: List<Float>,
    val entries: List<SessionHistoryEntry>,
) {
    companion object {
        fun initial(): SessionHistoryState =
            SessionHistoryState(
                weeklyTotal = 1024,
                highlightedDayIndex = 5,
                chartHeights = listOf(24f, 36f, 32f, 16f, 40f, 48f),
                entries = listOf(
                    SessionHistoryEntry(
                        mantra = "Om Mani Padme Hum",
                        timeLabel = "TODAY",
                        chantCount = 108,
                        durationLabel = "12:45 total",
                    ),
                    SessionHistoryEntry(
                        mantra = "Gayatri Mantra",
                        timeLabel = "YESTERDAY",
                        chantCount = 54,
                        durationLabel = "08:20 total",
                    ),
                    SessionHistoryEntry(
                        mantra = "Om Namah Shivaya",
                        timeLabel = "OCT 24, 2023",
                        chantCount = 108,
                        durationLabel = "15:30 total",
                    ),
                    SessionHistoryEntry(
                        mantra = "Lokah Samastah",
                        timeLabel = "OCT 22, 2023",
                        chantCount = 21,
                        durationLabel = "04:15 total",
                    ),
                ),
            )
    }
}

data class SessionHistoryEntry(
    val mantra: String,
    val timeLabel: String,
    val chantCount: Int,
    val durationLabel: String,
)

@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit,
) {
    val state = remember { SessionHistoryState.initial() }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 672.dp)
                .padding(top = 32.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp),
        ) {
            WeeklyProgressCard(state = state)
            HistorySection(entries = state.entries)
            ContinueJourneyCard()
        }
    }
}
