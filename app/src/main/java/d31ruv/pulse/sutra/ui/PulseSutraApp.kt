package d31ruv.pulse.sutra.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.core.data.chant.model.PulseSutraTab
import d31ruv.pulse.sutra.ui.navigation.PulseSutraNavGraph

@Composable
fun PulseSutraApp(appState: PulseSutraAppState) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val selectedTab = appState.currentTab()

    PulseSutraDashboard(
        isOffline = isOffline,
        selectedTab = selectedTab,
        onTabSelected = appState::selectTab,
        navGraph = {
            PulseSutraNavGraph(
                navController = appState.navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        },
    )
}

@Composable
private fun PulseSutraDashboard(
    isOffline: Boolean,
    selectedTab: PulseSutraTab,
    onTabSelected: (PulseSutraTab) -> Unit,
    navGraph: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBFF)),
    ) {
        BackgroundOrbs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 136.dp),
        ) {
            DashboardTopBar()

            if (isOffline) {
                OfflineBadge(
                    modifier = Modifier.padding(top = 12.dp),
                )
            }

            // NavHost fills the weighted slot previously occupied by when(selectedTab)
            navGraph()
        }

        BottomNavigationShell(
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun DashboardTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SanctuaryMark()
            Text(
                text = "Digital Sanctuary",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp,
                ),
                color = Color(0xFF393835),
            )
        }

        IconButtonShell {
            SettingsGlyph()
        }
    }
}

@Composable
internal fun FeaturePlaceholder(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.widthIn(max = 320.dp),
            shape = RoundedCornerShape(28.dp),
            color = Color(0xF2FFFBFF),
            shadowElevation = 18.dp,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = Color(0xFF393835),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ),
                    color = Color(0xFF666461),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationShell(
    selectedTab: PulseSutraTab,
    onTabSelected: (PulseSutraTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 18.dp,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    spotColor = Color(0x1A925600),
                )
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(Color(0xCCFFFBFF))
                .padding(horizontal = 28.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PulseSutraTab.entries.forEach { tab ->
                if (tab == selectedTab) {
                    SelectedBottomNavItem(tab = tab)
                } else {
                    InactiveBottomNavItem(
                        tab = tab,
                        onClick = { onTabSelected(tab) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectedBottomNavItem(tab: PulseSutraTab) {
    Box(
        modifier = Modifier
            .offset(y = (-12).dp)
            .size(64.dp)
            .shadow(
                elevation = 16.dp,
                shape = CircleShape,
                spotColor = Color(0x332C1600),
            )
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFB56E03), Color(0xFFF49D37)),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            BottomNavIcon(tab = tab, tint = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tab.label,
                color = Color.White,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.4.sp,
                ),
            )
        }
    }
}

@Composable
private fun InactiveBottomNavItem(
    tab: PulseSutraTab,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomNavIcon(tab = tab, tint = Color(0xFF8C8884))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tab.label,
            color = Color(0xFF8C8884),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.4.sp,
            ),
        )
    }
}

@Composable
private fun BottomNavIcon(
    tab: PulseSutraTab,
    tint: Color,
) {
    when (tab) {
        PulseSutraTab.Chant -> HaloGlyph(tint = tint, iconSize = 18.dp)
        PulseSutraTab.Target -> TargetGlyph(tint = tint, iconSize = 18.dp)
        PulseSutraTab.Journal -> JournalGlyph(tint = tint, iconSize = 18.dp)
    }
}

@Composable
private fun OfflineBadge(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Offline mode",
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0x1A925600))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        color = Color(0xFF925600),
        style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
        ),
    )
}

@Composable
private fun IconButtonShell(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.size(40.dp),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Composable
private fun SanctuaryMark() {
    Canvas(modifier = Modifier.size(18.dp)) {
        val stroke = Stroke(width = 1.8.dp.toPx(), cap = StrokeCap.Round)
        val gold = Color(0xFF925600)
        drawArc(
            color = gold,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.18f, size.height * 0.12f),
            size = Size(size.width * 0.64f, size.height * 0.42f),
            style = stroke,
        )
        drawLine(
            color = gold,
            start = Offset(size.width * 0.2f, size.height * 0.62f),
            end = Offset(size.width * 0.8f, size.height * 0.62f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = gold,
            start = Offset(size.width * 0.5f, size.height * 0.28f),
            end = Offset(size.width * 0.5f, size.height * 0.62f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = gold,
            start = Offset(size.width * 0.32f, size.height * 0.52f),
            end = Offset(size.width * 0.2f, size.height * 0.78f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = gold,
            start = Offset(size.width * 0.68f, size.height * 0.52f),
            end = Offset(size.width * 0.8f, size.height * 0.78f),
            strokeWidth = 1.8.dp.toPx(),
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun SettingsGlyph() {
    Canvas(modifier = Modifier.size(18.dp)) {
        val tint = Color(0xFF8C8884)
        val strokeWidth = 1.6.dp.toPx()
        drawCircle(
            color = tint,
            radius = size.minDimension * 0.18f,
            style = Stroke(width = strokeWidth),
        )
        repeat(8) { index ->
            val angle = Math.toRadians((index * 45.0))
            val start = Offset(
                x = center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.26f,
                y = center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.26f,
            )
            val end = Offset(
                x = center.x + kotlin.math.cos(angle).toFloat() * size.minDimension * 0.42f,
                y = center.y + kotlin.math.sin(angle).toFloat() * size.minDimension * 0.42f,
            )
            drawLine(
                color = tint,
                start = start,
                end = end,
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
    }
}

@Composable
private fun HaloGlyph(
    tint: Color,
    iconSize: Dp,
) {
    Canvas(modifier = Modifier.size(iconSize)) {
        val strokeWidth = 1.8.dp.toPx()
        drawCircle(
            color = tint,
            radius = this.size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = tint,
            radius = this.size.minDimension * 0.08f,
        )
    }
}

@Composable
private fun TargetGlyph(
    tint: Color,
    iconSize: Dp,
) {
    Canvas(modifier = Modifier.size(iconSize)) {
        val strokeWidth = 1.6.dp.toPx()
        drawCircle(
            color = tint,
            radius = this.size.minDimension * 0.34f,
            style = Stroke(width = strokeWidth),
        )
        drawCircle(
            color = tint,
            radius = this.size.minDimension * 0.08f,
        )
    }
}

@Composable
private fun JournalGlyph(
    tint: Color,
    iconSize: Dp,
) {
    Canvas(modifier = Modifier.size(iconSize)) {
        val strokeWidth = 1.6.dp.toPx()
        drawRect(
            color = tint,
            topLeft = Offset(this.size.width * 0.18f, this.size.height * 0.18f),
            size = Size(this.size.width * 0.56f, this.size.height * 0.64f),
            style = Stroke(width = strokeWidth),
        )
        drawLine(
            color = tint,
            start = Offset(this.size.width * 0.22f, this.size.height * 0.24f),
            end = Offset(this.size.width * 0.72f, this.size.height * 0.74f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun BackgroundOrbs() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .offset(x = (-108).dp, y = 292.dp)
                .size(256.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color(0x14925600), Color.Transparent),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 48.dp, y = (-148).dp)
                .size(192.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(Color(0x1AF49D37), Color.Transparent),
                    ),
                ),
        )
    }
}
