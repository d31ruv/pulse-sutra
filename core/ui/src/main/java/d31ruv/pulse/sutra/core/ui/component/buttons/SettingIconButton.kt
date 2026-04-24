package d31ruv.pulse.sutra.core.ui.component.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import d31ruv.pulse.sutra.core.ui.R
import kotlinx.coroutines.delay

@Composable
fun SettingIconButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    var rotate by rememberSaveable { mutableFloatStateOf(0f) }
    val animatedRotate by animateFloatAsState(rotate)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    IconButton(
        modifier = modifier,
        onClick = onClick,
        interactionSource = interactionSource,
    ) {
        Icon(
            modifier = Modifier.rotate(animatedRotate),
            painter = painterResource(R.drawable.ic_setting),
            contentDescription = "Settings",
        )
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            while (true) {
                rotate += 10f
                delay(16) // ~60fps smooth rotation
            }
        }
    }
}