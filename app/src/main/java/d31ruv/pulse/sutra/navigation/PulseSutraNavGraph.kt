package d31ruv.pulse.sutra.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import d31ruv.pulse.sutra.feature.chant.navigation.ChantRoute
import d31ruv.pulse.sutra.feature.chant.navigation.chantScreen
import d31ruv.pulse.sutra.feature.journal.navigation.journalScreen
import d31ruv.pulse.sutra.feature.settings.navigation.settingsScreen
import d31ruv.pulse.sutra.feature.target.navigation.targetScreen

/**
 * Root [NavHost] that wires all top-level tab destinations using type-safe
 * Jetpack Compose Navigation routes backed by kotlinx.serialization.
 *
 * Content modifier is forwarded so the host fills the column weight slot
 * already reserved by [PulseSutraNavGraph].
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
        enterTransition = {
            fadeIn(animationSpec = tween(delayMillis = 90)) + scaleIn(
                initialScale = 0.9f, animationSpec = tween(delayMillis = 90)
            )
        },
        exitTransition = { fadeOut() },
    ) {
        chantScreen()
        targetScreen()
        journalScreen()
        settingsScreen()
    }
}
