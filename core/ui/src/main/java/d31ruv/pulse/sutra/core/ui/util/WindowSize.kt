package d31ruv.pulse.sutra.core.ui.util

import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun WindowInfo.getMaxContentWidth(): Dp {
    val screenWidth = containerDpSize.width
    return when {
        screenWidth < 600.dp -> screenWidth
        screenWidth < 840.dp -> 600.dp
        else -> 840.dp
    }
}