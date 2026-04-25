package d31ruv.pulse.sutra.feature.target

import org.junit.Assert.assertEquals
import org.junit.Test

class TargetStateTest {
    @Test
    fun initial_state_defaults_to_om_mani_padme_hum_and_108() {
        val state = TargetState.initial()

        assertEquals("Om Mani Padme Hum", state.selectedMantra)
        assertEquals(108, state.selectedTarget)
    }

    @Test
    fun select_target_updates_target_and_slider_position() {
        val state = TargetState.initial().selectTarget(1008)

        assertEquals(1008, state.selectedTarget)
        assertEquals(1f, state.sliderPosition)
    }

    @Test
    fun select_mantra_updates_selected_value() {
        val state = TargetState.initial().selectMantra("Gayatri Mantra")

        assertEquals("Gayatri Mantra", state.selectedMantra)
    }
}
