package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp

@Composable
internal fun SparkGlyph() {
    Canvas(modifier = Modifier.size(12.dp)) {
        val tint = Color(0xE6FFFFFF)
        val strokeWidth = 1.5.dp.toPx()
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
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.12f,
            style = Fill,
        )
    }
}

@Composable
internal fun ArrowGlyph() {
    Canvas(modifier = Modifier.size(width = 10.dp, height = 12.dp)) {
        val strokeWidth = 1.8.dp.toPx()
        drawLine(
            color = Color.White,
            start = Offset(0f, size.height / 2f),
            end = Offset(size.width * 0.72f, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.45f, size.height * 0.2f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = Color.White,
            start = Offset(size.width * 0.45f, size.height * 0.8f),
            end = Offset(size.width, size.height / 2f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}
