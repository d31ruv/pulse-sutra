package d31ruv.pulse.sutra.util

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun rememberNavigationSuiteType(): NavigationSuiteType {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val configuration = LocalConfiguration.current

    return remember(configuration.orientation, adaptiveInfo.windowSizeClass) {
        if (configuration.orientation == ORIENTATION_LANDSCAPE && configuration.smallestScreenWidthDp < 600  // phones only
        ) {
            NavigationSuiteType.NavigationRail
        } else { // fall to default for others (tablets etc.) as they are 600+
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }
}