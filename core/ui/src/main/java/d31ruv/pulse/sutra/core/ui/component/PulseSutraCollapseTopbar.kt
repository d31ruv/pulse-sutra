package d31ruv.pulse.sutra.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.component.buttons.SettingIconButton
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

/**
 * A collapsable top bar for the Pulse Sutra app.
 * In collapsed state, it only shows the title.
 * In expanded state, it shows both title and subtitle.
 *
 * @param title The title text.
 * @param modifier The modifier for the top bar.
 * @param subtitle The optional subtitle text.
 * @param navigationIcon The optional leading icon.
 * @param actions The optional trailing icons.
 * @param scrollBehavior The scroll behavior for collapsing.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseSutraCollapseTopbar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {
            val collapsedFraction = scrollBehavior?.state?.collapsedFraction ?: 0f
            Column {
                Text(
                    text = title, style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp,
                    ), maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                if (subtitle != null) {
                    val subtitleAlpha = 1f - (collapsedFraction * 2f).coerceIn(0f, 1f)
                    if (subtitleAlpha > 0.05f) {
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = subtitleAlpha),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        },
        navigationIcon = navigationIcon,
        actions = actions,
        scrollBehavior = scrollBehavior,
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PulseSutraCollapseToolbarPreview() {
    PulseSutraTheme {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                PulseSutraCollapseTopbar(
                    title = stringResource(R.string.core_ui_chant),
                    subtitle = stringResource(R.string.core_ui_ritual_of_focus),
                    navigationIcon = {
                        val fraction = scrollBehavior.state.collapsedFraction
                        val iconAlpha = ((fraction - 0.2f) / 0.6f).coerceIn(0f, 1f)

                        Icon(
                            modifier = Modifier
                                .minimumInteractiveComponentSize()
                                .alpha(iconAlpha),
                            painter = painterResource(R.drawable.ic_sanctuary_mark),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    actions = {
                        SettingIconButton(onClick = {})
                    },
                    scrollBehavior = scrollBehavior
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(50) { index ->
                    ListItem(
                        headlineContent = { Text(text = "Item $index") },
                    )
                }
            }
        }
    }
}
