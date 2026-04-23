package d31ruv.pulse.sutra.feature.chant.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import d31ruv.pulse.sutra.core.ui.component.PrimaryButton
import d31ruv.pulse.sutra.core.ui.theme.PulseSutraTheme

@Composable
internal fun StartSessionButton(
    modifier: Modifier = Modifier,
) {
    val typography = MaterialTheme.typography

    PrimaryButton(
        modifier = modifier,
        onClick = {
            // TODO
        },
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(R.drawable.ic_play),
            contentDescription = "Play",
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = stringResource(R.string.core_ui_start_session),
            style = typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Preview
@Composable
private fun StartSessionButtonPreview() {
    PulseSutraTheme {
        StartSessionButton()
    }
}
