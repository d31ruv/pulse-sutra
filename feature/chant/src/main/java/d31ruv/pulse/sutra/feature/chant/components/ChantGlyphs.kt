package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun PlayGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val path = Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.15f)
            lineTo(size.width * 0.8f, size.height * 0.5f)
            lineTo(size.width * 0.2f, size.height * 0.85f)
            close()
        }
        drawPath(path = path, color = Color.White)
    }
}

@Composable
internal fun PlusGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        val tint = Color(0xFF666461)
        val strokeWidth = 1.8.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, 0f),
            end = Offset(size.width / 2f, size.height),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(0f, size.height / 2f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun TimerGlyph() {
    Canvas(modifier = Modifier.size(16.dp)) {
        val tint = Color(0xFF8C8884)
        val strokeWidth = 1.8.dp.toPx()
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawLine(
            color = tint,
            start = center,
            end = Offset(center.x, size.height * 0.26f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = center,
            end = Offset(size.width * 0.65f, size.height * 0.5f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.3f, size.height * 0.1f),
            end = Offset(size.width * 0.5f, 0f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun StopGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        drawRect(
            color = Color(0xFFCA4E38),
            topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
            size = Size(size.width * 0.64f, size.height * 0.64f),
            style = Stroke(width = 1.6.dp.toPx()),
        )
    }
}
