package d31ruv.pulse.sutra.feature.settings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val settingsSurfaceColor = Color(0xFFFFFBFF)
private val settingsCardColor = Color(0xFFF7F1EA)
private val settingsChipColor = Color(0xFFE8E0D7)
private val settingsSubtleTextColor = Color(0xFF9B938A)
private val settingsTextColor = Color(0xFF393835)
private val settingsGold = Color(0xFFAF6D09)
private val settingsDangerContainerColor = Color(0xFFFFEBEB)
private val settingsDangerTextColor = Color(0xFFDA5942)
private val settingsActionColor = Color(0xFFC2632F)

data class SettingsState(
    val profileName: String,
    val profileLevel: String,
    val vibrationFeedbackEnabled: Boolean,
    val soundFeedbackEnabled: Boolean,
    val voiceActivatedCountingEnabled: Boolean,
    val voiceSensitivity: Float,
    val darkModeEnabled: Boolean,
) {
    fun toggleVibration(): SettingsState =
        copy(vibrationFeedbackEnabled = !vibrationFeedbackEnabled)

    fun toggleSound(): SettingsState =
        copy(soundFeedbackEnabled = !soundFeedbackEnabled)

    fun toggleVoiceActivatedCounting(): SettingsState =
        copy(voiceActivatedCountingEnabled = !voiceActivatedCountingEnabled)

    fun updateVoiceSensitivity(value: Float): SettingsState =
        copy(voiceSensitivity = value.coerceIn(0f, 1f))

    fun toggleDarkMode(): SettingsState =
        copy(darkModeEnabled = !darkModeEnabled)

    companion object {
        fun initial(): SettingsState =
            SettingsState(
                profileName = "Morning Seeker",
                profileLevel = "LEVEL 12 - 432 MANTRAS",
                vibrationFeedbackEnabled = true,
                soundFeedbackEnabled = false,
                voiceActivatedCountingEnabled = true,
                voiceSensitivity = 0.75f,
                darkModeEnabled = false,
            )
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    var state by remember {
        mutableStateOf(SettingsState.initial())
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 672.dp)
                .padding(top = 20.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SettingsHeader()
            ProfileCard(state = state)
            SettingsSectionLabel(text = "SENSORY FEEDBACK")
            ToggleCard(
                icon = { VibrationGlyph() },
                title = "Vibration Feedback",
                checked = state.vibrationFeedbackEnabled,
                onToggle = { state = state.toggleVibration() },
            )
            ToggleCard(
                icon = { SoundGlyph() },
                title = "Sound Feedback",
                checked = state.soundFeedbackEnabled,
                onToggle = { state = state.toggleSound() },
            )

            SettingsSectionLabel(text = "INTELLIGENT COUNTING")
            ToggleCard(
                icon = { VoiceGlyph() },
                title = "Voice-Activated Counting",
                subtitle = "App listens for your mantra to auto-count",
                checked = state.voiceActivatedCountingEnabled,
                onToggle = { state = state.toggleVoiceActivatedCounting() },
            )
            VoiceSensitivityCard(
                sensitivity = state.voiceSensitivity,
                onSensitivityChanged = { state = state.updateVoiceSensitivity(it) },
            )

            SettingsSectionLabel(text = "ENVIRONMENT")
            ToggleCard(
                icon = { MoonGlyph() },
                title = "Dark Mode",
                checked = state.darkModeEnabled,
                onToggle = { state = state.toggleDarkMode() },
            )

            SessionArchiveCard()
        }
    }
}

@Composable
private fun SettingsHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Preferences",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                lineHeight = 40.sp,
                letterSpacing = (-0.9).sp,
            ),
            color = settingsTextColor,
        )
        Text(
            text = "Tailor your meditation environment to your rhythm.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = settingsSubtleTextColor,
        )
    }
}

@Composable
private fun ProfileCard(
    state: SettingsState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(settingsSurfaceColor)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFF514740)),
            contentAlignment = Alignment.Center,
        ) {
            ProfileGlyph()
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = state.profileName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                ),
                color = settingsTextColor,
            )
            Text(
                text = state.profileLevel,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.8.sp,
                ),
                color = settingsGold,
            )
        }
    }
}

@Composable
private fun SettingsSectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.9.sp,
        ),
        color = Color(0xFFB7AEA4),
    )
}

@Composable
private fun ToggleCard(
    icon: @Composable () -> Unit,
    title: String,
    checked: Boolean,
    onToggle: () -> Unit,
    subtitle: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(settingsSurfaceColor)
            .padding(horizontal = 14.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconContainer(content = icon)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                ),
                color = settingsTextColor,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                    ),
                    color = settingsSubtleTextColor,
                )
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = { onToggle() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = settingsGold,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFE0DDD8),
                uncheckedBorderColor = Color.Transparent,
                checkedBorderColor = Color.Transparent,
            ),
        )
    }
}

@Composable
private fun VoiceSensitivityCard(
    sensitivity: Float,
    onSensitivityChanged: (Float) -> Unit,
) {
    var sliderValue by remember(sensitivity) {
        mutableFloatStateOf(sensitivity)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(settingsSurfaceColor)
            .padding(horizontal = 14.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconContainer {
                SensitivityGlyph()
            }
            Text(
                text = "Voice Sensitivity",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                ),
                color = settingsTextColor,
            )
            Text(
                text = "${(sliderValue * 100).toInt()}%",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = settingsGold,
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                onSensitivityChanged(it)
            },
            colors = SliderDefaults.colors(
                thumbColor = settingsGold,
                activeTrackColor = settingsGold,
                inactiveTrackColor = settingsChipColor,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Soft",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                ),
                color = settingsSubtleTextColor,
            )
            Text(
                text = "Loud",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                ),
                color = settingsSubtleTextColor,
            )
        }
    }
}

@Composable
private fun SessionArchiveCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(settingsDangerContainerColor)
            .padding(horizontal = 14.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Session Archive",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                ),
                color = settingsDangerTextColor,
            )
            Text(
                text = "This will permanently delete all mantra history.",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                ),
                color = Color(0xFFA77A75),
            )
        }
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(999.dp),
                    spotColor = Color(0x33C2632F),
                )
                .clip(RoundedCornerShape(999.dp))
                .background(settingsActionColor)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "CLEAR\nHISTORY",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp,
                    lineHeight = 12.sp,
                ),
                color = Color.White,
            )
        }
    }
}

@Composable
private fun IconContainer(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(settingsCardColor),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
private fun ProfileGlyph() {
    Canvas(modifier = Modifier.size(22.dp)) {
        val strokeWidth = 1.4.dp.toPx()
        drawCircle(
            color = Color.White,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width / 2f, size.height * 0.34f),
        )
        drawArc(
            color = Color.White,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.18f, size.height * 0.36f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.64f, size.height * 0.42f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        drawLine(
            color = Color(0xFFF2C879),
            start = Offset(size.width * 0.2f, size.height * 0.82f),
            end = Offset(size.width * 0.8f, size.height * 0.82f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun VibrationGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width * 0.34f, size.height * 0.2f),
            end = Offset(size.width * 0.34f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.66f, size.height * 0.2f),
            end = Offset(size.width * 0.66f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.12f, size.height * 0.34f),
            end = Offset(size.width * 0.2f, size.height * 0.26f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.12f, size.height * 0.66f),
            end = Offset(size.width * 0.2f, size.height * 0.74f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.8f, size.height * 0.26f),
            end = Offset(size.width * 0.88f, size.height * 0.34f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.8f, size.height * 0.74f),
            end = Offset(size.width * 0.88f, size.height * 0.66f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun SoundGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width * 0.22f, size.height * 0.38f),
            end = Offset(size.width * 0.38f, size.height * 0.38f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.38f, size.height * 0.38f),
            end = Offset(size.width * 0.58f, size.height * 0.2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.38f, size.height * 0.38f),
            end = Offset(size.width * 0.58f, size.height * 0.56f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.58f, size.height * 0.2f),
            end = Offset(size.width * 0.58f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawArc(
            color = tint,
            startAngle = -32f,
            sweepAngle = 64f,
            useCenter = false,
            topLeft = Offset(size.width * 0.52f, size.height * 0.26f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.28f, size.height * 0.48f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
    }
}

@Composable
private fun VoiceGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, size.height * 0.18f),
            end = Offset(size.width / 2f, size.height * 0.56f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawArc(
            color = tint,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(size.width * 0.28f, size.height * 0.42f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.44f, size.height * 0.28f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, size.height * 0.7f),
            end = Offset(size.width / 2f, size.height * 0.86f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.34f, size.height * 0.86f),
            end = Offset(size.width * 0.66f, size.height * 0.86f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun SensitivityGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val strokeWidth = 1.6.dp.toPx()
        listOf(0.2f, 0.42f, 0.64f, 0.84f).forEachIndexed { index, x ->
            val lineHeight = 0.3f + (index * 0.12f)
            drawLine(
                color = settingsGold,
                start = Offset(size.width * x, size.height * (1f - lineHeight)),
                end = Offset(size.width * x, size.height * lineHeight),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
    }
}

@Composable
private fun MoonGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        drawCircle(
            color = settingsGold,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.54f, size.height * 0.46f),
        )
        drawCircle(
            color = settingsCardColor,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.64f, size.height * 0.36f),
        )
    }
}
