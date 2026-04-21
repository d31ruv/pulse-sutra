package d31ruv.pulse.sutra.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    colors: PrimaryButtonColors = PrimaryButtonDefaults.primaryButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val containerBrush by colors.containerBrush(enabled)
    val contentColor by colors.contentColor(enabled)

    Row(
        modifier = Modifier
            .semantics { role = Role.Button }
            .sizeIn(
                minHeight = ButtonDefaults.MinHeight, minWidth = ButtonDefaults.MinWidth
            )
            .shadow(
                elevation = if (enabled) 4.dp else 0.dp,
                shape = shape,
            )
            .background(
                brush = containerBrush,
                shape = shape,
            )
            .clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button
            )
            .padding(contentPadding) then modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

@Immutable
class PrimaryButtonColors(
    val containerColor: Color,
    val containerGradientColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {
    @Composable
    fun containerBrush(enabled: Boolean): State<Brush> {
        val brush =
            remember(enabled, containerColor, containerGradientColor, disabledContainerColor) {
                if (enabled) {
                    Brush.linearGradient(listOf(containerColor, containerGradientColor))
                } else {
                    Brush.linearGradient(listOf(disabledContainerColor, disabledContainerColor))
                }
            }
        return rememberUpdatedState(brush)
    }

    @Composable
    fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }
}

object PrimaryButtonDefaults {
    @Composable
    @ReadOnlyComposable
    fun primaryButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        containerGradientColor: Color = MaterialTheme.colorScheme.inversePrimary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    ): PrimaryButtonColors = PrimaryButtonColors(
        containerColor = containerColor,
        containerGradientColor = containerGradientColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    PulseSutraTheme {
        PrimaryButton(onClick = {}) {
            Text(text = "Primary Button")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonDisabledPreview() {
    PulseSutraTheme {
        PrimaryButton(onClick = {}, enabled = false) {
            Text(text = "Disabled Button")
        }
    }
}
