package d31ruv.pulse.sutra.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

enum class PulseSutraTab(@get:StringRes val label: Int, @get:DrawableRes val icon: Int) {
    Chant(
        label = R.string.core_ui_chant,
        icon = R.drawable.ic_chant,
    ),
    Target(
        label = R.string.core_ui_target,
        icon = R.drawable.ic_target,
    ),
    Journal(
        label = R.string.core_ui_journal,
        icon = R.drawable.ic_journal,
    ),
}

fun NavigationSuiteScope.pulseSutraNavigationItem(
    tab: PulseSutraTab,
    selected: Boolean,
    onClick: () -> Unit,
    navigationItemColor: NavigationSuiteItemColors? = null,
) {
    item(
        selected = selected,
        onClick = onClick,
        icon = {
            val colorScheme = MaterialTheme.colorScheme
            val brush = Brush.linearGradient(
                colors = listOf(colorScheme.primary, colorScheme.inversePrimary)
            )

            // Animations
            val rotationX by animateFloatAsState(
                targetValue = if (selected && tab == PulseSutraTab.Target) 180f else 0f,
                label = "rotationX",
                animationSpec = spring(
                    Spring.DampingRatioHighBouncy,
                    Spring.StiffnessLow,
                ),
            )

            val rotationY by animateFloatAsState(
                targetValue = if (selected && tab == PulseSutraTab.Chant) 180f else 0f,
                label = "rotationY",
                animationSpec = spring(
                    Spring.DampingRatioHighBouncy,
                    Spring.StiffnessLow,
                )
            )

            val tilt by animateFloatAsState(
                targetValue = if (selected && tab == PulseSutraTab.Journal) 15f else 0f,
                label = "tilt",
                animationSpec = spring(
                    Spring.DampingRatioHighBouncy,
                    Spring.StiffnessLow,
                )
            )

            Icon(
                painter = painterResource(tab.icon),
                contentDescription = stringResource(tab.label),
                modifier = Modifier
                    .then(
                        if (selected) {
                            Modifier.background(brush = brush, shape = CircleShape)
                        } else Modifier
                    )
                    .graphicsLayer {
                        this.rotationX = rotationX  // Flip Y-axis
                        this.rotationY = rotationY  // Flip X-axis
                        this.rotationZ = tilt   // Tilt
                        cameraDistance = 8 * density
                    }
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            )
        },
        label = {
            val typography = MaterialTheme.typography
            Text(
                text = stringResource(tab.label),
                style = typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            )
        },
        colors = navigationItemColor,
    )
}

@Preview
@Composable
private fun PulseSutraNavigationItemPreview() {
    var selectedTab by remember { mutableStateOf(PulseSutraTab.Chant) }
    PulseSutraTheme {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                PulseSutraTab.entries.forEach { tab ->
                    pulseSutraNavigationItem(
                        selected = tab == selectedTab,
                        onClick = { selectedTab = tab },
                        tab = tab,
                    )
                }
            },
        )
    }
}
