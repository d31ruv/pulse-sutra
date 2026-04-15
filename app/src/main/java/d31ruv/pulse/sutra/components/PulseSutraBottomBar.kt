package d31ruv.pulse.sutra.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import d31ruv.pulse.sutra.R
import d31ruv.pulse.sutra.core.designsystem.theme.PulseSutraTheme

enum class PulseSutraTab(@get:StringRes val label: Int, @get:DrawableRes val icon: Int) {
    Chant(
        label = R.string.lbl_chant,
        icon = R.drawable.ic_chant,
    ),
    Target(
        label = R.string.lbl_target,
        icon = R.drawable.ic_target,
    ),
    Journal(
        label = R.string.lbl_journal,
        icon = R.drawable.ic_journal,
    ),
}

@Composable
fun PulseSutraBottomBar(
    selectedTab: PulseSutraTab,
    onTabSelected: (PulseSutraTab) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val brush = Brush.linearGradient(
        colors = listOf(colorScheme.primary, colorScheme.inversePrimary)
    )

    AnimatedVisibility(visible = visible, enter = expandVertically(), exit = shrinkVertically()) {
        BottomAppBar(modifier = modifier, containerColor = colorScheme.surface) {
            PulseSutraTab.entries.forEach { tab ->
                val selected = tab == selectedTab
                val size by animateDpAsState(if (selected) 18.dp else 36.dp)

                NavigationBarItem(
                    selected = selected,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        Column(
                            modifier = Modifier
                                .then(
                                    if (selected) {
                                        Modifier
                                            .background(brush = brush, shape = CircleShape)
                                            .padding(horizontal = 8.dp)
                                    } else Modifier,
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                modifier = Modifier.size(size),
                                painter = painterResource(tab.icon),
                                contentDescription = stringResource(tab.label)
                            )
                            AnimatedVisibility(selected) {
                                Text(
                                    lineHeight = 20.sp,
                                    text = stringResource(tab.label),
                                    style = typography.labelSmall,
                                )
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.surface,
                        selectedTextColor = colorScheme.surface,
                        indicatorColor = Color.Transparent,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PulseSutraBottomBarPreview() {
    var selectedTab by remember { mutableStateOf(PulseSutraTab.Chant) }
    PulseSutraTheme {
        PulseSutraBottomBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
        )
    }
}
