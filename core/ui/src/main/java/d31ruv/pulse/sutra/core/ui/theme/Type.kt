package d31ruv.pulse.sutra.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import d31ruv.pulse.sutra.core.ui.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.core_ui_com_google_android_gms_fonts_certs,
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Manrope"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Manrope"),
        fontProvider = provider,
    )
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily), // 57.sp
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily), // 45.sp
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily), // 36.sp
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily), // 32.sp
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily), // 28.sp
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily), // 24.sp
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily), // 22.sp
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily), // 16.sp
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily), // 14.sp
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily), // 16.sp
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily), // 14.sp
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily), // 12.sp
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily), // 14.sp
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily), // 12.sp
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily), // 11.sp
)
