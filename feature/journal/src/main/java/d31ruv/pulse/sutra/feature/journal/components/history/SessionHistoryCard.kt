package d31ruv.pulse.sutra.feature.journal.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.feature.journal.SessionHistoryEntry

@Composable
fun SessionHistoryItem(entry: SessionHistoryEntry) {
    val shapes = MaterialTheme.shapes
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = shapes.medium)
            .background(colorScheme.surfaceContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_chant),
                contentDescription = "Chant",
                tint = colorScheme.primary,
            )
        }
        Spacer(Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = entry.mantra,
                    style = typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = entry.timeLabel,
                    style = typography.labelSmall,
                    color = colorScheme.onSurfaceVariant,
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.scale(.6f),
                    painter = painterResource(R.drawable.ic_target),
                    contentDescription = "Target",
                    tint = colorScheme.onSurfaceVariant,
                )
                Text(
                    text = stringResource(R.string.core_ui_chants_format, entry.chantCount),
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.width(16.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_duration),
                    contentDescription = "Target",
                    tint = colorScheme.onSurfaceVariant,
                )
                Text(
                    text = entry.durationLabel,
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
