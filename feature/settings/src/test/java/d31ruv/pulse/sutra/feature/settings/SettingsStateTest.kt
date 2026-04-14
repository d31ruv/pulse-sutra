package d31ruv.pulse.sutra.feature.settings

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsStateTest {
    @Test
    fun initial_state_matches_mockup_defaults() {
        val state = SettingsState.initial()

        assertEquals("Morning Seeker", state.profileName)
        assertTrue(state.vibrationFeedbackEnabled)
        assertFalse(state.soundFeedbackEnabled)
        assertTrue(state.voiceActivatedCountingEnabled)
        assertEquals(0.75f, state.voiceSensitivity)
        assertFalse(state.darkModeEnabled)
    }

    @Test
    fun update_voice_sensitivity_clamps_values() {
        val state = SettingsState.initial().updateVoiceSensitivity(1.4f)

        assertEquals(1f, state.voiceSensitivity)
    }
}
