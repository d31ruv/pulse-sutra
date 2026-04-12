package d31ruv.pulse.sutra.feature.target

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

private val targetSurfaceColor = Color(0xFFFDF9F5)
private val targetCardColor = Color(0xFFF1EDE9)
private val targetChipColor = Color(0xFFEBE8E3)
private val targetTextColor = Color(0xFF393835)
private val targetSubtleTextColor = Color(0xFF666461)
private val targetLabelColor = Color(0xFF925600)
private val targetGoldGradient = listOf(Color(0xFF925600), Color(0xFFF49D37))

data class TargetDashboardState(
    val mantras: List<String>,
    val selectedMantra: String,
    val availableTargets: List<Int>,
    val quickTargets: List<Int>,
    val selectedTarget: Int,
) {
    val sliderPosition: Float
        get() = availableTargets.indexOf(selectedTarget).coerceAtLeast(0).toFloat() /
            availableTargets.lastIndex.coerceAtLeast(1)

    fun selectMantra(mantra: String): TargetDashboardState =
        copy(selectedMantra = mantra)

    fun selectTarget(target: Int): TargetDashboardState =
        copy(selectedTarget = target)

    companion object {
        fun initial(): TargetDashboardState =
            TargetDashboardState(
                mantras = listOf(
                    "Om Mani Padme Hum",
                    "So Hum",
                    "Om Namah Shivaya",
                    "Gayatri Mantra",
                ),
                selectedMantra = "Om Mani Padme Hum",
                availableTargets = listOf(27, 54, 108, 216, 324, 540, 1008),
                quickTargets = listOf(27, 54, 108, 1008),
                selectedTarget = 108,
            )
    }
}

@Composable
fun TargetDashboardScreen(
    modifier: Modifier = Modifier,
) {
    var selectedMantra by rememberSaveable { mutableStateOf(TargetDashboardState.initial().selectedMantra) }
    var selectedTarget by rememberSaveable { mutableStateOf(TargetDashboardState.initial().selectedTarget) }
    val baseState = remember { TargetDashboardState.initial() }
    val screenState = remember(selectedMantra, selectedTarget) {
        baseState
            .selectMantra(selectedMantra)
            .selectTarget(selectedTarget)
    }

    TargetDashboardContent(
        state = screenState,
        onMantraSelected = { selectedMantra = it },
        onTargetSelected = { selectedTarget = it },
        modifier = modifier,
    )
}

@Composable
internal fun TargetDashboardContent(
    state: TargetDashboardState,
    onMantraSelected: (String) -> Unit,
    onTargetSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 672.dp)
                .padding(top = 28.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Set Your Intention",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 36.sp,
                        lineHeight = 40.sp,
                        letterSpacing = (-0.9).sp,
                    ),
                    color = targetTextColor,
                )
                Text(
                    text = "Configure your mantra session for a focused path.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ),
                    color = targetSubtleTextColor,
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                MantraSelectionCard(
                    state = state,
                    onMantraSelected = onMantraSelected,
                )
                TargetSelectionCard(
                    state = state,
                    onTargetSelected = onTargetSelected,
                )
                RitualAnchorCard()
            }

            BeginSessionButton()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MantraSelectionCard(
    state: TargetDashboardState,
    onMantraSelected: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SectionLabel(text = "CHOOSE MANTRA")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(targetCardColor)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(
                text = state.selectedMantra,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                ),
                color = targetTextColor,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.mantras
                    .filterNot { it == state.selectedMantra }
                    .forEach { mantra ->
                        AssistChip(
                            label = mantra,
                            selected = false,
                            onClick = { onMantraSelected(mantra) },
                        )
                    }
            }
        }
    }
}

@Composable
private fun TargetSelectionCard(
    state: TargetDashboardState,
    onTargetSelected: (Int) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            SectionLabel(text = "REPETITION TARGET")
            Text(
                text = state.selectedTarget.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp,
                    lineHeight = 40.sp,
                    letterSpacing = (-1.8).sp,
                ),
                color = targetLabelColor,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(targetSurfaceColor)
                .padding(horizontal = 24.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            TargetSlider(
                state = state,
                onTargetSelected = onTargetSelected,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                state.quickTargets.forEach { target ->
                    QuickTargetChip(
                        target = target,
                        selected = state.selectedTarget == target,
                        modifier = Modifier.weight(1f),
                        onClick = { onTargetSelected(target) },
                    )
                }
            }
        }
    }
}

@Composable
private fun TargetSlider(
    state: TargetDashboardState,
    onTargetSelected: (Int) -> Unit,
) {
    var sliderValue by remember(state.selectedTarget) { mutableFloatStateOf(state.sliderPosition) }
    val trackGradient = remember {
        Brush.horizontalGradient(targetGoldGradient)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .align(Alignment.Center),
        ) {
            val strokeHeight = 8.dp.toPx()
            val y = size.height / 2f - strokeHeight / 2f
            drawRoundRect(
                color = targetChipColor,
                topLeft = Offset(0f, y),
                size = Size(size.width, strokeHeight),
                cornerRadius = CornerRadius(strokeHeight, strokeHeight),
            )
            drawRoundRect(
                brush = trackGradient,
                topLeft = Offset(0f, y),
                size = Size(size.width * sliderValue, strokeHeight),
                cornerRadius = CornerRadius(strokeHeight, strokeHeight),
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = { value ->
                sliderValue = value
                val index = (value * state.availableTargets.lastIndex).roundToInt()
                    .coerceIn(0, state.availableTargets.lastIndex)
                onTargetSelected(state.availableTargets[index])
            },
            valueRange = 0f..1f,
            steps = state.availableTargets.lastIndex - 1,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun RitualAnchorCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF6EFE6), Color(0xFFE1E5E8), Color(0xFFB1B1AC)),
                ),
            ),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(156.dp)
                .clip(CircleShape)
                .background(Color(0x12F49D37)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp)
                .size(width = 126.dp, height = 104.dp)
                .clip(RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp, bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE38A4A), Color(0xFFB8642F)),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 86.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFD7C4)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 82.dp, start = 52.dp)
                .size(width = 10.dp, height = 32.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF7D6158)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 82.dp, end = 52.dp)
                .size(width = 10.dp, height = 32.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF7D6158)),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0x66000000)),
                    ),
                ),
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 18.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SparkGlyph()
            Text(
                text = "RITUAL OF FOCUS",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                ),
                color = Color(0xE6FFFFFF),
            )
        }
    }
}

@Composable
private fun BeginSessionButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = CircleShape,
                spotColor = Color(0x33925600),
            )
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(targetGoldGradient),
            )
            .padding(horizontal = 32.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Begin Session",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                letterSpacing = (-0.45).sp,
            ),
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(12.dp))
        ArrowGlyph()
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
        ),
        color = targetLabelColor,
    )
}

@Composable
private fun AssistChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val background = if (selected) targetLabelColor else targetChipColor
    val content = if (selected) Color.White else targetTextColor
    Text(
        text = label,
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        color = content,
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
    )
}

@Composable
private fun QuickTargetChip(
    target: Int,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val containerColor = if (selected) targetLabelColor else targetChipColor
    val textColor = if (selected) Color.White else targetTextColor
    val chipModifier = if (selected) {
        modifier.shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp),
            spotColor = Color(0x33925600),
        )
    } else {
        modifier
    }

    Box(
        modifier = chipModifier
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0x1AFFFFFF),
                        shape = RoundedCornerShape(10.dp),
                    ),
            )
        }
        Text(
            text = target.toString(),
            color = textColor,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        )
    }
}

@Composable
private fun SparkGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        val tint = Color(0xE6FFFFFF)
        val strokeWidth = 1.5.dp.toPx()
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
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.12f,
            style = Fill,
        )
    }
}

@Composable
private fun ArrowGlyph() {
    Canvas(modifier = Modifier.size(width = 10.dp, height = 12.dp)) {
        val strokeWidth = 1.8.dp.toPx()
        drawLine(
            color = Color.White,
            start = Offset(0f, size.height / 2f),
            end = Offset(size.width * 0.72f, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.45f, size.height * 0.2f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.45f, size.height * 0.8f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}
