package d31ruv.pulse.sutra.feature.journal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.feature.journal.SessionHistoryState

@Composable
internal fun WeeklyProgressCard(
    state: SessionHistoryState,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(journalSurfaceColor)
            .padding(horizontal = 32.dp, vertical = 32.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp)
                .size(128.dp)
                .clip(CircleShape)
                .background(Color(0x33F49D37)),
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "WEEKLY PROGRESS",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp,
                ),
                color = journalSubtleTextColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total Chants\nThis Week",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp,
                    lineHeight = 40.sp,
                    letterSpacing = (-0.9).sp,
                ),
                color = journalPrimaryColor,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "%,d".format(state.weeklyTotal),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 60.sp,
                        lineHeight = 60.sp,
                    ),
                    color = journalTextColor,
                )
                Text(
                    text = "repetitions",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ),
                    color = journalSubtleTextColor,
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

@Composable
private fun WeeklyBarChart(
    chartHeights: List<Float>,
    highlightedDayIndex: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        chartHeights.forEachIndexed { index, height ->
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(height.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(
                        color = if (index == highlightedDayIndex) {
                            journalPrimaryColor
                        } else {
                            Color(0x4DF49D37)
                        },
                    ),
            )
        }
    }
}
