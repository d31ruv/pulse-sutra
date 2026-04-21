package d31ruv.pulse.sutra.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.core.ui.theme.DarkTheme
import d31ruv.pulse.sutra.feature.settings.components.MoonGlyph
import d31ruv.pulse.sutra.feature.settings.components.ProfileCard
import d31ruv.pulse.sutra.feature.settings.components.SessionArchiveCard
import d31ruv.pulse.sutra.feature.settings.components.SettingsHeader
import d31ruv.pulse.sutra.feature.settings.components.SettingsSectionLabel
import d31ruv.pulse.sutra.feature.settings.components.SoundGlyph
import d31ruv.pulse.sutra.feature.settings.components.ToggleCard
import d31ruv.pulse.sutra.feature.settings.components.VibrationGlyph
import d31ruv.pulse.sutra.feature.settings.components.VoiceGlyph
import d31ruv.pulse.sutra.feature.settings.components.VoiceSensitivityCard

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
    val darkTheme by DarkTheme.enabled.collectAsStateWithLifecycle()

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
            SettingsSectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.ui.R.string.core_ui_sensory_feedback))
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

            SettingsSectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.ui.R.string.core_ui_intelligent_counting))
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

            SettingsSectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.ui.R.string.core_ui_environment))
            ToggleCard(
                icon = { MoonGlyph() },
                title = "Dark Mode",
                checked = darkTheme,
                onToggle = { DarkTheme.setEnabled(!darkTheme) },
            )

            SettingsSectionLabel(text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.ui.R.string.core_ui_data_privacy))
            SessionArchiveCard()
        }
    }
}


