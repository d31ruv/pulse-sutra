package d31ruv.pulse.sutra.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.chant.navigation.ChantRoute
import d31ruv.pulse.sutra.feature.chant.navigation.chantScreen
import d31ruv.pulse.sutra.feature.target.navigation.targetScreen
import d31ruv.pulse.sutra.ui.FeaturePlaceholder
import kotlinx.serialization.Serializable

/** Type-safe route for the Journal Reflections destination. */
@Serializable
object JournalRoute

/**
 * Root [NavHost] that wires all top-level tab destinations using type-safe
 * Jetpack Compose Navigation routes backed by kotlinx.serialization.
 *
 * Content modifier is forwarded so the host fills the column weight slot
 * already reserved by [PulseSutraDashboard].
 */
@Composable
fun PulseSutraNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ChantRoute,
        modifier = modifier,
    ) {
        chantScreen()
        targetScreen()

        composable<JournalRoute> {
            FeaturePlaceholder(
                title = "Journal Reflections",
                message = "Capture each session's intention, energy, and closing thoughts.",
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
