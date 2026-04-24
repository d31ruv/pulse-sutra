package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
internal fun ChantCounter(
    currentCount: Int,
    targetCount: Int,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val animatedCurrentCount by animateIntAsState(currentCount, label = "currentCountAnimation")
    val animatedTargetCount by animateIntAsState(targetCount, label = "targetCountAnimation")
    val animatedProgress by animateFloatAsState(
        targetValue = (animatedCurrentCount.toFloat() / animatedTargetCount).coerceIn(0f, 1f),
        label = "progressAnimation",
    )

    Box(
        modifier = modifier.sizeIn(minWidth = 320.dp, minHeight = 320.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.matchParentSize(),
            progress = { animatedProgress },
            strokeWidth = 8.dp,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = animatedCurrentCount.toString(),
                color = colorScheme.onSurface,
                style = typography.displayLarge.copy(
                    fontSize = 128.sp,
                    fontWeight = FontWeight.ExtraLight,
                ),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.core_ui_target),
                    color = colorScheme.onSurfaceVariant,
                    style = typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                )
                Text(
                    text = animatedTargetCount.toString(),
                    color = colorScheme.primary,
                    style = typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChantCounterPreview() {
    PulseSutraTheme {
        ChantCounter(currentCount = 50, targetCount = 100)
    }
}
