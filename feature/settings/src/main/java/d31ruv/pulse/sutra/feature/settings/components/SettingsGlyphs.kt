package d31ruv.pulse.sutra.feature.settings.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
internal fun VibrationGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width * 0.34f, size.height * 0.2f),
            end = Offset(size.width * 0.34f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.66f, size.height * 0.2f),
            end = Offset(size.width * 0.66f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.12f, size.height * 0.34f),
            end = Offset(size.width * 0.2f, size.height * 0.26f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.12f, size.height * 0.66f),
            end = Offset(size.width * 0.2f, size.height * 0.74f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.8f, size.height * 0.26f),
            end = Offset(size.width * 0.88f, size.height * 0.34f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.8f, size.height * 0.74f),
            end = Offset(size.width * 0.88f, size.height * 0.66f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun SoundGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width * 0.22f, size.height * 0.38f),
            end = Offset(size.width * 0.38f, size.height * 0.38f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.38f, size.height * 0.38f),
            end = Offset(size.width * 0.58f, size.height * 0.2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.38f, size.height * 0.38f),
            end = Offset(size.width * 0.58f, size.height * 0.56f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.58f, size.height * 0.2f),
            end = Offset(size.width * 0.58f, size.height * 0.8f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawArc(
            color = tint,
            startAngle = -32f,
            sweepAngle = 64f,
            useCenter = false,
            topLeft = Offset(size.width * 0.52f, size.height * 0.26f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.28f, size.height * 0.48f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
    }
}

@Composable
internal fun VoiceGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val tint = settingsGold
        val strokeWidth = 1.6.dp.toPx()
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, size.height * 0.18f),
            end = Offset(size.width / 2f, size.height * 0.56f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawArc(
            color = tint,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(size.width * 0.28f, size.height * 0.42f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.44f, size.height * 0.28f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        drawLine(
            color = tint,
            start = Offset(size.width / 2f, size.height * 0.7f),
            end = Offset(size.width / 2f, size.height * 0.86f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = tint,
            start = Offset(size.width * 0.34f, size.height * 0.86f),
            end = Offset(size.width * 0.66f, size.height * 0.86f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
internal fun SensitivityGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        val strokeWidth = 1.6.dp.toPx()
        listOf(0.2f, 0.42f, 0.64f, 0.84f).forEachIndexed { index, x ->
            val lineHeight = 0.3f + (index * 0.12f)
            drawLine(
                color = settingsGold,
                start = Offset(size.width * x, size.height * (1f - lineHeight)),
                end = Offset(size.width * x, size.height * lineHeight),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
    }
}

@Composable
internal fun MoonGlyph() {
    Canvas(modifier = Modifier.size(14.dp)) {
        drawCircle(
            color = settingsGold,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.54f, size.height * 0.46f),
        )
        drawCircle(
            color = settingsCardColor,
            radius = size.minDimension * 0.28f,
            center = Offset(size.width * 0.64f, size.height * 0.36f),
        )
    }
}
