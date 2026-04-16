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
            text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.core.designsystem.R.string.core_designsystem_preferences),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = androidx.compose.ui.res.stringResource(d31ruv.pulse.sutra.feature.settings.R.string.feature_settings_tailor_environment),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
