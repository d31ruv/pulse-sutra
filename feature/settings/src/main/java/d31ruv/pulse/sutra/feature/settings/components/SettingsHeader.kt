package d31ruv.pulse.sutra.feature.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SettingsHeader() {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Preferences",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                lineHeight = 40.sp,
                letterSpacing = (-0.9).sp,
            ),
            color = settingsTextColor,
        )
        Text(
            text = "Tailor your meditation environment to your rhythm.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = settingsSubtleTextColor,
        )
    }
}
