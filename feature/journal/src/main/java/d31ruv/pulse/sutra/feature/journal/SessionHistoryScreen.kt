package d31ruv.pulse.sutra.feature.journal

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private const val zenImageUrl =
    "https://www.figma.com/api/mcp/asset/3a578ed0-2c2b-49bd-b05b-c0c4b031f8a3"

private val journalSurfaceColor = Color(0xFFFDF9F5)
private val journalCardColor = Color.White
private val journalPrimaryColor = Color(0xFF925600)
private val journalAccentColor = Color(0xFFF49D37)
private val journalTextColor = Color(0xFF393835)
private val journalSubtleTextColor = Color(0xFF666461)
private val journalChipColor = Color(0x1A925600)

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
fun SessionHistoryScreen(
    modifier: Modifier = Modifier,
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

@Composable
private fun WeeklyProgressCard(
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

@Composable
private fun HistorySection(
    entries: List<SessionHistoryEntry>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "History",
            modifier = Modifier.padding(start = 32.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = (-0.4).sp,
            ),
            color = journalTextColor,
        )

        entries.forEach { entry ->
            SessionHistoryCard(entry = entry)
        }
    }
}

@Composable
private fun SessionHistoryCard(
    entry: SessionHistoryEntry,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color(0x08925600),
            )
            .clip(RoundedCornerShape(12.dp))
            .background(journalCardColor)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(journalChipColor),
            contentAlignment = Alignment.Center,
        ) {
            HistoryMandalaIcon()
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = entry.mantra,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                    ),
                    color = journalTextColor,
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = entry.timeLabel,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp,
                    ),
                    color = journalSubtleTextColor,
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SessionHistoryMeta(
                    text = "${entry.chantCount} Chants",
                    icon = { ChantCountGlyph() },
                )
                SessionHistoryMeta(
                    text = entry.durationLabel,
                    icon = { SessionDurationGlyph() },
                )
            }
        }
    }
}

@Composable
private fun SessionHistoryMeta(
    text: String,
    icon: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = journalSubtleTextColor,
        )
    }
}

@Composable
private fun ContinueJourneyCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp)
            .shadow(
                elevation = 24.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color(0x1A000000),
            )
            .clip(RoundedCornerShape(16.dp)),
    ) {
        AsyncImage(
            model = zenImageUrl,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0x99000000)),
                    ),
                ),
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp),
        ) {
            Text(
                text = "Continue your journey",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                ),
                color = Color(0xCCFFFFFF),
            )
            Text(
                text = "Find Inner Peace Today",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                ),
                color = Color.White,
            )
        }
    }
}

@Composable
private fun HistoryMandalaIcon() {
    Canvas(modifier = Modifier.size(20.dp)) {
        val strokeWidth = 1.6.dp.toPx()
        drawCircle(
            color = journalPrimaryColor,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = journalAccentColor,
            radius = size.minDimension * 0.12f,
        )
    }
}

@Composable
private fun ChantCountGlyph() {
    Canvas(modifier = Modifier.size(10.dp)) {
        val strokeWidth = 1.4.dp.toPx()
        drawCircle(
            color = journalSubtleTextColor,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = journalSubtleTextColor,
            radius = size.minDimension * 0.08f,
        )
    }
}

@Composable
private fun SessionDurationGlyph() {
    Canvas(modifier = Modifier.size(10.dp)) {
        val strokeWidth = 1.4.dp.toPx()
        drawCircle(
            color = journalSubtleTextColor,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawLine(
            color = journalSubtleTextColor,
            start = center,
            end = Offset(center.x, size.height * 0.24f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = journalSubtleTextColor,
            start = center,
            end = Offset(size.width * 0.66f, size.height * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}
