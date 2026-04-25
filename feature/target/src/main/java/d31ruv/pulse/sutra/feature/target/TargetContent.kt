package d31ruv.pulse.sutra.feature.target

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme
import d31ruv.pulse.sutra.core.ui.util.getMaxContentWidth
import d31ruv.pulse.sutra.feature.target.components.BeginSessionButton
import d31ruv.pulse.sutra.feature.target.components.MantraSelectionCard
import d31ruv.pulse.sutra.feature.target.components.RitualAnchorCard
import d31ruv.pulse.sutra.feature.target.components.TargetSelectionCard
import d31ruv.pulse.sutra.feature.target.components.TargetTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TargetContent(
    state: TargetState = TargetState(),
    onMantraSelected: (String) -> Unit = {},
    onTargetSelected: (Int) -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    val windowInfo = LocalWindowInfo.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            TargetTopBar(
                scrollBehavior = scrollBehavior,
                onSettingsClick = onSettingsClick,
            )
        },
    ) { innerPadding ->
        Box(
            Modifier
                .verticalScroll(state = scrollState)
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.widthIn(max = windowInfo.getMaxContentWidth()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(16.dp))
                MantraSelectionCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    selectedMantra = state.selectedMantra,
                    mantras = state.mantras,
                    onMantraSelected = onMantraSelected,
                )
                Spacer(modifier = Modifier.height(40.dp))
                TargetSelectionCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    state = state,
                    onTargetSelected = onTargetSelected,
                )
                Spacer(modifier = Modifier.height(40.dp))
                RitualAnchorCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    scrollState = scrollState,
                )
                Spacer(modifier = Modifier.height(16.dp))
                BeginSessionButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .heightIn(68.dp)
                        .fillMaxWidth(),
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun TargetContentPreview() {
    PulseSutraTheme {
        TargetContent()
    }
}
