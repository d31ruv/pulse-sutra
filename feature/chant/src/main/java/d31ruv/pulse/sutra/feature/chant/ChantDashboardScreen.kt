package d31ruv.pulse.sutra.feature.chant

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState

/**
 * Entry-point composable for the Chant destination.
 * Obtains [ChantViewModel] via Hilt and delegates to [ChantDashboardContent].
 */
@Composable
fun ChantDashboardScreen(
    viewModel: ChantViewModel = hiltViewModel(),
) {
    val state by viewModel.dashboardState.collectAsStateWithLifecycle()
    val resolvedState = state ?: return
    ChantDashboardContent(state = resolvedState)
}

@Composable
internal fun ChantDashboardContent(
    state: ChantDashboardState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            text = state.mantraLabel,
            color = Color(0xFF925600),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
            ),
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = state.mantraName,
            color = Color(0xFF393835),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                lineHeight = 36.sp,
                letterSpacing = (-0.75).sp,
            ),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(14.dp))

        ChantCounter(
            currentCount = state.currentCount,
            targetCount = state.targetCount,
        )

        Spacer(modifier = Modifier.height(36.dp))

        PrimaryActionButton(label = state.primaryActionLabel)

        Spacer(modifier = Modifier.height(16.dp))

        SecondaryActionRow(manualActionLabel = state.manualActionLabel)
    }
}

@Composable
private fun ChantCounter(
    currentCount: Int,
    targetCount: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(320.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 4.dp.toPx()
            val inset = strokeWidth * 2
            val arcSize = size.minDimension - inset * 2
            val topLeft = Offset(inset, inset)

            drawArc(
                color = Color(0xFFE7E2DB),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = Size(arcSize, arcSize),
                style = Stroke(width = strokeWidth),
            )

            listOf(249f, 339f, 69f, 159f).forEach { startAngle ->
                drawArc(
                    color = Color(0xFF9F6202),
                    startAngle = startAngle,
                    sweepAngle = 42f,
                    useCenter = false,
                    topLeft = topLeft,
                    size = Size(arcSize, arcSize),
                    style = Stroke(
                        width = strokeWidth * 1.8f,
                        cap = StrokeCap.Round,
                    ),
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = currentCount.toString(),
                color = Color(0xFF393835),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 126.sp,
                    lineHeight = 126.sp,
                    letterSpacing = (-6.4).sp,
                ),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "TARGET",
                    color = Color(0xFF666461),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.2.sp,
                    ),
                )
                Text(
                    text = targetCount.toString(),
                    color = Color(0xFF925600),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }
}

@Composable
private fun PrimaryActionButton(
    label: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(68.dp)
            .shadow(
                elevation = 20.dp,
                shape = CircleShape,
                spotColor = Color(0x4D925600),
            )
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFB56E03), Color(0xFFF49D37)),
                ),
            )
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayGlyph()
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 28.sp,
            ),
        )
    }
}

@Composable
private fun SecondaryActionRow(
    manualActionLabel: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SecondaryActionButton(
            label = manualActionLabel,
            containerColor = Color(0xFFEBE8E3),
            modifier = Modifier.weight(1f),
        ) {
            PlusGlyph()
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MiniActionButton(
                containerColor = Color(0xFFFDF9F5),
                modifier = Modifier.weight(1f),
            ) {
                TimerGlyph()
            }

            MiniActionButton(
                containerColor = Color(0x1AFD795A),
                modifier = Modifier.weight(1f),
            ) {
                StopGlyph()
            }
        }
    }
}

@Composable
private fun SecondaryActionButton(
    label: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = Color(0xFF393835),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        )
    }
}

@Composable
private fun MiniActionButton(
    containerColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor),
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}

// ---------------------------------------------------------------------------
// Private glyph composables (self-contained so the module has no UI leakage
// back into :app)
// ---------------------------------------------------------------------------

@Composable
private fun PlayGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val path = androidx.compose.ui.graphics.Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.15f)
            lineTo(size.width * 0.8f, size.height * 0.5f)
            lineTo(size.width * 0.2f, size.height * 0.85f)
            close()
        }
        drawPath(path = path, color = Color.White)
    }
}

@Composable
private fun PlusGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        val tint = Color(0xFF666461)
        val strokeWidth = 1.8.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, 0f),
            end = Offset(size.width / 2f, size.height),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(0f, size.height / 2f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun TimerGlyph() {
    Canvas(modifier = Modifier.size(16.dp)) {
        val tint = Color(0xFF8C8884)
        val strokeWidth = 1.8.dp.toPx()
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawLine(
            color = tint,
            start = center,
            end = Offset(center.x, size.height * 0.26f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = center,
            end = Offset(size.width * 0.65f, size.height * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.3f, size.height * 0.1f),
            end = Offset(size.width * 0.5f, 0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun StopGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        drawRect(
            color = Color(0xFFCA4E38),
            topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
            size = Size(size.width * 0.64f, size.height * 0.64f),
            style = Stroke(width = 1.6.dp.toPx()),
        )
    }
}
