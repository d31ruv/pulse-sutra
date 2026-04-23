package d31ruv.pulse.sutra.feature.chant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.data.chant.model.ChantDashboardState
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme
import d31ruv.pulse.sutra.core.ui.util.getMaxContentWidth
import d31ruv.pulse.sutra.feature.chant.components.ChantCounter
import d31ruv.pulse.sutra.feature.chant.components.ChantTopBar
import d31ruv.pulse.sutra.feature.chant.components.SecondaryActionRow
import d31ruv.pulse.sutra.feature.chant.components.buttons.StartSessionButton

@Composable
internal fun ChantContent(
    state: ChantDashboardState = ChantDashboardState(),
    onSettingsClick: () -> Unit = {},
) {
    val windowInfo = LocalWindowInfo.current
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Scaffold(
        containerColor = Color.Transparent,
        topBar = { ChantTopBar(onSettingsClick = onSettingsClick) },
    ) { innerPadding ->
        Box(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.widthIn(max = windowInfo.getMaxContentWidth()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(R.string.core_ui_current_mantra),
                    color = colorScheme.primary,
                    style = typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = state.mantraName,
                    color = colorScheme.onSurface,
                    style = typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                )
                Spacer(modifier = Modifier.height(32.dp))
                ChantCounter(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    currentCount = state.currentCount,
                    targetCount = state.targetCount,
                )
                Spacer(modifier = Modifier.height(48.dp))
                StartSessionButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(68.dp)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryActionRow(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(60.dp)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun ChantContentPreview() {
    PulseSutraTheme {
        ChantContent()
    }
}
