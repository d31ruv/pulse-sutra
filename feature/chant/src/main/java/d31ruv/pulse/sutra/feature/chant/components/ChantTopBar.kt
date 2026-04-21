package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.component.SettingIconButton
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

private const val TAG = "ChantTopBar"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChantTopBar(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorScheme.surface.copy(.9f)),
        title = {
            Text(
                text = stringResource(R.string.core_ui_chant),
                style = typography.titleLarge.copy(
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
        actions = { SettingIconButton(onSettingsClick) },
    )
}

@Preview
@Composable
private fun ChantTopBarPreview() {
    PulseSutraTheme {
        ChantTopBar(onSettingsClick = {})
    }
}
