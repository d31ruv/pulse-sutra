package d31ruv.pulse.sutra.core.data.chant.repository

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultChantDashboardRepositoryTest {
    @Test
    fun dashboardState_exposesDefaultChantingContent() {
        val repository = DefaultChantDashboardRepository()

        val state = repository.dashboardState.replayCacheOrFirst()

        assertEquals("Digital Sanctuary", state.sanctuaryTitle)
        assertEquals("Om Namah Shivaya", state.mantraName)
        assertEquals(54, state.currentCount)
        assertEquals(108, state.targetCount)
    }
}
