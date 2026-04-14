package d31ruv.pulse.sutra.feature.target.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun RitualAnchorCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF6EFE6), Color(0xFFE1E5E8), Color(0xFFB1B1AC)),
                ),
            ),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(156.dp)
                .clip(CircleShape)
                .background(Color(0x12F49D37)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp)
                .size(width = 126.dp, height = 104.dp)
                .clip(RoundedCornerShape(topStart = 64.dp, topEnd = 64.dp, bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE38A4A), Color(0xFFB8642F)),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 86.dp)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFD7C4)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 82.dp, start = 52.dp)
                .size(width = 10.dp, height = 32.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF7D6158)),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 82.dp, end = 52.dp)
                .size(width = 10.dp, height = 32.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF7D6158)),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0x66000000)),
                    ),
                ),
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 18.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SparkGlyph()
            Text(
                text = "RITUAL OF FOCUS",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                ),
                color = Color(0xE6FFFFFF),
            )
        }
    }
}
