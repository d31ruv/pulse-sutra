package d31ruv.pulse.sutra.feature.chant.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.chant.ChantScreen
import kotlinx.serialization.Serializable

/**
 * Type-safe route object for the Chant dashboard destination.
 * Used with Jetpack Compose Navigation's [composable] overload that accepts
 * a reified type parameter backed by kotlinx.serialization.
 */
@Serializable
object ChantRoute

/**
 * Registers the Chant dashboard screen in the given [NavGraphBuilder].
 */
fun NavGraphBuilder.chantScreen(onSettingsClick: () -> Unit) {
    composable<ChantRoute> {
        ChantScreen(onSettingsClick = onSettingsClick)
    }
}
