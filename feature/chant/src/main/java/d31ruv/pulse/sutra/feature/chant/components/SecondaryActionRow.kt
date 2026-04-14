package d31ruv.pulse.sutra.feature.chant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SecondaryActionRow(
    manualActionLabel: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SecondaryActionButton(
            label = manualActionLabel,
            containerColor = Color(0xFFEBE8E3),
            modifier = Modifier.weight(1f),
        ) {
            PlusGlyph()
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MiniActionButton(
                containerColor = Color(0xFFFDF9F5),
                modifier = Modifier.weight(1f),
            ) {
                TimerGlyph()
            }

            MiniActionButton(
                containerColor = Color(0x1AFD795A),
                modifier = Modifier.weight(1f),
            ) {
                StopGlyph()
            }
        }
    }
}

@Composable
private fun SecondaryActionButton(
    label: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = Color(0xFF393835),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        )
    }
}

@Composable
private fun MiniActionButton(
    containerColor: Color,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor),
        contentAlignment = Alignment.Center,
    ) {
        icon()
    }
}
