package d31ruv.pulse.sutra.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.R
import d31ruv.pulse.sutra.core.designsystem.theme.PulseSutraTheme

@Composable
fun OfflineBadge(modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Text(
        text = stringResource(R.string.lbl_offline_mode),
        modifier = modifier
            .clip(CircleShape)
            .background(colorScheme.primaryContainer)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = colorScheme.onPrimaryContainer,
        style = typography.labelMedium.copy(fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
    )
}

@Preview
@Composable
private fun OfflineBadgePreview() {
    PulseSutraTheme {
        OfflineBadge()
    }
}
