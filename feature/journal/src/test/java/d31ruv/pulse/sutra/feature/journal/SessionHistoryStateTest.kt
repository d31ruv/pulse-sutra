package d31ruv.pulse.sutra.feature.journal

import org.junit.Assert.assertEquals
import org.junit.Test

class SessionHistoryStateTest {
    @Test
    fun initial_state_exposes_expected_weekly_total_and_entries() {
        val state = SessionHistoryState.initial()

        assertEquals(1024, state.weeklyTotal)
        assertEquals(4, state.entries.size)
    }

    @Test
    fun initial_state_highlights_last_chart_bar() {
        val state = SessionHistoryState.initial()

        assertEquals(5, state.highlightedDayIndex)
        assertEquals(48f, state.chartHeights.last())
    }
}
