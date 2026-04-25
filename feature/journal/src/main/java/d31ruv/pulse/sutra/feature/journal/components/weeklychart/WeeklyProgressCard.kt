package d31ruv.pulse.sutra.feature.journal.components.weeklychart

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.util.parallaxLayoutModifier
import d31ruv.pulse.sutra.feature.journal.SessionHistoryState

@Composable
internal fun WeeklyProgressCard(
    modifier: Modifier = Modifier,
    state: SessionHistoryState = SessionHistoryState.initial(),
    scrollState: ScrollState = ScrollState(0),
) {
    val shapes = MaterialTheme.shapes
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = modifier
            .clip(shapes.medium)
            .background(colorScheme.surfaceContainer)
            .padding(32.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .parallaxLayoutModifier(scrollState, 5),
        ) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(colorScheme.primary.copy(alpha = .2f))
                    .scale(1.25f),
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.core_ui_weekly_progress),
                style = typography.labelSmall,
                color = colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.core_ui_total_chants_week),
                style = typography.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
                color = colorScheme.primary,
                textAlign = TextAlign.Center,
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "%,d".format(state.weeklyTotal),
                    style = typography.displayLarge.copy(
                        fontSize = 60.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.core_ui_repetitions),
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            WeeklyBarChart(
                chartHeights = state.chartHeights,
                highlightedDayIndex = state.highlightedDayIndex,
            )
        }
    }
}
