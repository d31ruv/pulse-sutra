package d31ruv.pulse.sutra.feature.journal.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.util.parallaxLayoutModifier

@Composable
internal fun ContinueJourneyCard(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = ScrollState(0),
) {
    val shapes = MaterialTheme.shapes
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .heightIn(min = 190.dp)
            .fillMaxWidth()
            .clip(shapes.medium)
    ) {
        Image(
            modifier = Modifier
                .alpha(.9f)
                .matchParentSize()
                .parallaxLayoutModifier(scrollState = scrollState, rate = 8)
                .scale(1.7f),
            painter = painterResource(R.drawable.ic_continue_journey),
            contentDescription = "Meditation Background",
            contentScale = ContentScale.Crop,
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
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.core_ui_continue_journey),
                style = typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                color = Color.White, // hard coded color as image itself is black
            )
            Text(
                text = stringResource(R.string.core_ui_find_inner_peace),
                style = typography.bodyLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                color = Color.White, // hard coded color as image itself is black
            )
        }
    }
}
