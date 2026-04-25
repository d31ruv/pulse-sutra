package d31ruv.pulse.sutra.feature.journal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme
import d31ruv.pulse.sutra.core.ui.util.getMaxContentWidth
import d31ruv.pulse.sutra.feature.journal.components.ContinueJourneyCard
import d31ruv.pulse.sutra.feature.journal.components.JournalTopBar
import d31ruv.pulse.sutra.feature.journal.components.history.HistorySection
import d31ruv.pulse.sutra.feature.journal.components.weeklychart.WeeklyProgressCard

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JournalContent(
    state: SessionHistoryState = SessionHistoryState.initial(),
    onSettingsClick: () -> Unit = {},
) {
    val windowInfo = LocalWindowInfo.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            JournalTopBar(scrollBehavior = scrollBehavior, onSettingsClick = onSettingsClick)
        },
    ) { innerPadding ->
        Box(
            Modifier
                .verticalScroll(state = scrollState)
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.widthIn(max = windowInfo.getMaxContentWidth()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(32.dp))
                WeeklyProgressCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    state = state,
                    scrollState = scrollState,
                )
                Spacer(Modifier.height(48.dp))
                HistorySection(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    entries = state.entries,
                )
                Spacer(Modifier.height(48.dp))
                ContinueJourneyCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    scrollState = scrollState,
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun JournalContentPreview() {
    PulseSutraTheme {
        JournalContent()
    }
}