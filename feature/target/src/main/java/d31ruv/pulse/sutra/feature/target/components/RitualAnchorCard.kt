package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

private fun Modifier.parallaxLayoutModifier(
    scrollState: ScrollState,
    rate: Int,
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val yOffset = if (rate > 0) scrollState.value / rate else scrollState.value

    layout(placeable.width, placeable.height) {
        placeable.place(0, yOffset)
    }
}

@Composable
internal fun RitualAnchorCard(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
) {
    val shapes = MaterialTheme.shapes
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .heightIn(min = 140.dp)
            .fillMaxWidth()
            .clip(shapes.medium)
    ) {
        Image(
            modifier = Modifier
                .alpha(.9f)
                .matchParentSize()
                .parallaxLayoutModifier(scrollState = scrollState, rate = 10)
                .scale(1.25f),
            painter = painterResource(R.drawable.ic_meditation_background),
            contentDescription = "Meditation Background",
            contentScale = ContentScale.FillWidth,
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colorScheme.surface.copy(.1f),
                            colorScheme.onSurface.copy(.2f),
                            colorScheme.onSurface.copy(.3f),
                        ),
                    ),
                ),
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_ritual_focus),
                contentDescription = "Ritual Focus",
                tint = colorScheme.surface
            )
            Text(
                text = stringResource(R.string.core_ui_ritual_of_focus),
                style = typography.labelSmall,
                color = colorScheme.surface
            )
        }
    }
}

@Preview
@Composable
private fun RitualAnchorCardPreview() {
    PulseSutraTheme {
        RitualAnchorCard(scrollState = ScrollState(0))
    }
}
