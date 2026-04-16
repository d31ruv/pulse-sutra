package d31ruv.pulse.sutra.feature.journal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun HistoryMandalaIcon() {
    val colorScheme = androidx.compose.material3.MaterialTheme.colorScheme
    Canvas(modifier = Modifier.size(20.dp)) {
        val strokeWidth = 1.6.dp.toPx()
        drawCircle(
            color = colorScheme.primary,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = colorScheme.tertiary,
            radius = size.minDimension * 0.12f,
        )
    }
}

@Composable
internal fun ChantCountGlyph() {
    val colorScheme = androidx.compose.material3.MaterialTheme.colorScheme
    Canvas(modifier = Modifier.size(10.dp)) {
        val strokeWidth = 1.4.dp.toPx()
        drawCircle(
            color = colorScheme.onSurfaceVariant,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = colorScheme.onSurfaceVariant,
            radius = size.minDimension * 0.08f,
        )
    }
}

@Composable
internal fun SessionDurationGlyph() {
    val colorScheme = androidx.compose.material3.MaterialTheme.colorScheme
    Canvas(modifier = Modifier.size(10.dp)) {
        val strokeWidth = 1.4.dp.toPx()
        drawCircle(
            color = colorScheme.onSurfaceVariant,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawLine(
            color = colorScheme.onSurfaceVariant,
            start = center,
            end = Offset(center.x, size.height * 0.24f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = colorScheme.onSurfaceVariant,
            start = center,
            end = Offset(size.width * 0.66f, size.height * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}
