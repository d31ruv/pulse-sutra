package d31ruv.pulse.sutra.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.R
import d31ruv.pulse.sutra.core.designsystem.theme.PulseSutraTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(
    isSettingsDestination: Boolean,
    onSettingsToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val rotate by animateFloatAsState(if (isSettingsDestination) 180f else 0f)

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Digital Sanctuary",
                style = typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp,
                ),
                color = colorScheme.onSurface,
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.minimumInteractiveComponentSize(),
                painter = painterResource(R.drawable.ic_sanctuary_mark),
                contentDescription = "Sanctuary Mark",
                tint = colorScheme.primary,
            )
        },
        actions = {
            IconToggleButton(
                checked = isSettingsDestination,
                onCheckedChange = onSettingsToggle,
            ) {
                Icon(
                    modifier = Modifier.rotate(rotate),
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Settings",
                )
            }
        },
    )
}

@Preview
@Composable
private fun DashboardTopBarPreview() {
    PulseSutraTheme {
        DashboardTopBar(
            isSettingsDestination = false,
            onSettingsToggle = {},
        )
    }
}
