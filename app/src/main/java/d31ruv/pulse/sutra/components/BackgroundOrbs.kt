package d31ruv.pulse.sutra.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
fun BackgroundOrbs(modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    val spotlightBrush = Brush.radialGradient(
        colors = listOf(colorScheme.primary.copy(.5f), Color.Transparent),
    )

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .offset(x = (-108).dp, y = 292.dp)
                .size(256.dp)
                .clip(CircleShape)
                .background(spotlightBrush),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 48.dp, y = (-148).dp)
                .size(192.dp)
                .clip(CircleShape)
                .background(spotlightBrush),
        )
    }
}

@Preview
@Composable
private fun BackgroundOrbsPreview() {
    PulseSutraTheme {
        BackgroundOrbs(Modifier.fillMaxSize())
    }
}
