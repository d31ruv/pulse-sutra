package d31ruv.pulse.sutra.feature.chant.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
fun StopIconButton(modifier: Modifier = Modifier) {
    val shapes = MaterialTheme.shapes
    val colorScheme = MaterialTheme.colorScheme

    IconButton(
        modifier = modifier,
        shape = shapes.medium,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = colorScheme.errorContainer.copy(.5f),
            contentColor = colorScheme.error,
        ),
        onClick = {
            // TODO
        },
    ) {
        Icon(painter = painterResource(R.drawable.ic_stop), contentDescription = "Stop")
    }
}

@Preview
@Composable
private fun StopIconButtonPreview() {
    PulseSutraTheme {
        StopIconButton()
    }
}
