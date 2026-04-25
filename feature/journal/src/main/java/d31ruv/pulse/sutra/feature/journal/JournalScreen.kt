package d31ruv.pulse.sutra.feature.journal

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class SessionHistoryState(
    val weeklyTotal: Int,
    val highlightedDayIndex: Int,
    val chartHeights: List<Float>,
    val entries: List<SessionHistoryEntry>,
) {
    companion object {
        fun initial(): SessionHistoryState = SessionHistoryState(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalScreen(
    onSettingsClick: () -> Unit = {},
) {
    val state = remember { SessionHistoryState.initial() }

    JournalContent(state, onSettingsClick)
}
