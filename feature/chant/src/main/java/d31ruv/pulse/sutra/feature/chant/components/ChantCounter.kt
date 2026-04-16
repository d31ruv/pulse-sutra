package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ChantCounter(
    currentCount: Int,
    targetCount: Int,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    Box(
        modifier = modifier.size(320.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 4.dp.toPx()
            val inset = strokeWidth * 2
            val arcSize = size.minDimension - inset * 2
            val topLeft = Offset(inset, inset)

            drawArc(
                color = colorScheme.surfaceVariant,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = Size(arcSize, arcSize),
                style = Stroke(width = strokeWidth),
            )

            listOf(249f, 339f, 69f, 159f).forEach { startAngle ->
                drawArc(
                    color = colorScheme.primary,
                    startAngle = startAngle,
                    sweepAngle = 42f,
                    useCenter = false,
                    topLeft = topLeft,
                    size = Size(arcSize, arcSize),
                    style = Stroke(
                        width = strokeWidth * 1.8f,
                        cap = StrokeCap.Round,
                    ),
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = currentCount.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayLarge,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.designsystem.R.string.core_designsystem_target),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = targetCount.toString(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}
