package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme
import d31ruv.pulse.sutra.feature.chant.components.buttons.ManualCountButton
import d31ruv.pulse.sutra.feature.chant.components.buttons.ResetIconButton
import d31ruv.pulse.sutra.feature.chant.components.buttons.StopIconButton

@Composable
internal fun SecondaryActionRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ManualCountButton(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.5f),
        )
        ResetIconButton(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.25f),
        )
        StopIconButton(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.25f),
        )
    }
}

@Preview
@Composable
private fun SecondaryActionRowPreview() {
    PulseSutraTheme {
        SecondaryActionRow()
    }
}
