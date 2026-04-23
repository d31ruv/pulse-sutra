package d31ruv.pulse.sutra.feature.chant.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.core.ui.R
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
fun ManualCountButton(modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    val shapes = MaterialTheme.shapes

    Button(
        modifier = modifier,
        onClick = {
            // TODO
        },
        shape = shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.outline.copy(.25f),
            contentColor = colorScheme.onSurface,
        ),
    ) {
        Icon(painter = painterResource(R.drawable.ic_plus), contentDescription = "Plus")
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.core_ui_manual_count),
            style = typography.bodyMedium.copy(fontSize = 13.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Preview
@Composable
private fun ManualCountButtonPreview() {
    PulseSutraTheme {
        ManualCountButton()
    }
}
