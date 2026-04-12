package d31ruv.pulse.sutra.feature.journal.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.journal.SessionHistoryScreen
import kotlinx.serialization.Serializable

/** Type-safe route object for the journal history destination. */
@Serializable
object JournalRoute

/** Registers the journal history screen in the given [NavGraphBuilder]. */
fun NavGraphBuilder.journalScreen() {
    composable<JournalRoute> {
        SessionHistoryScreen()
    }
}
