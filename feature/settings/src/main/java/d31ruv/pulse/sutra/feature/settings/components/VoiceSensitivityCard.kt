package d31ruv.pulse.sutra.feature.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun VoiceSensitivityCard(
    sensitivity: Float,
    onSensitivityChanged: (Float) -> Unit,
) {
    var sliderValue by remember(sensitivity) {
        mutableFloatStateOf(sensitivity)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(settingsSurfaceColor)
            .padding(horizontal = 14.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconContainer {
                SensitivityGlyph()
            }
            Text(
                text = "Voice Sensitivity",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                ),
                color = settingsTextColor,
            )
            Text(
                text = "${(sliderValue * 100).toInt()}%",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = settingsGold,
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                onSensitivityChanged(it)
            },
            colors = SliderDefaults.colors(
                thumbColor = settingsGold,
                activeTrackColor = settingsGold,
                inactiveTrackColor = settingsChipColor,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
            ),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Soft",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                ),
                color = settingsSubtleTextColor,
            )
            Text(
                text = "Loud",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    lineHeight = 12.sp,
                ),
                color = settingsSubtleTextColor,
            )
        }
    }
}
