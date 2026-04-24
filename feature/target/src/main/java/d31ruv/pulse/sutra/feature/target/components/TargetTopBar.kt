package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.component.PulseSutraCollapseTopbar
import d31ruv.pulse.sutra.core.ui.component.buttons.SettingIconButton
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TargetTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
    onSettingsClick: () -> Unit = {},
) {
    val colorScheme = MaterialTheme.colorScheme
    val fraction = scrollBehavior.state.collapsedFraction
    val iconAlpha = ((fraction - 0.2f) / 0.6f).coerceIn(0f, 1f)

    PulseSutraCollapseTopbar(
        modifier = modifier,
        title = stringResource(R.string.core_ui_set_intention),
        subtitle = stringResource(R.string.core_ui_configure_mantra),
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .alpha(iconAlpha),
                painter = painterResource(R.drawable.ic_sanctuary_mark),
                contentDescription = "Sanctuary Mark",
                tint = colorScheme.primary,
            )
        },
        actions = {
            SettingIconButton(onClick = onSettingsClick)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.surface.copy(.9f),
            scrolledContainerColor = colorScheme.surface.copy(.9f),
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun TargetTopBarPreview() {
    PulseSutraTheme {
        TargetTopBar()
    }
}
