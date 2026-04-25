package d31ruv.pulse.sutra.feature.journal.components.weeklychart

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun WeeklyBarChart(
    chartHeights: List<Float>,
    highlightedDayIndex: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        chartHeights.forEachIndexed { index, height ->
            val colorScheme = MaterialTheme.colorScheme
            val animatedHeight by animateDpAsState(height.dp)
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .height(animatedHeight)
                    .clip(RoundedCornerShape(100))
                    .background(
                        color = if (index == highlightedDayIndex) {
                            colorScheme.primary
                        } else {
                            colorScheme.primary.copy(alpha = 0.3f)
                        },
                    ),
            )
        }
    }
}