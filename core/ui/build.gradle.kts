plugins {
    alias(libs.plugins.pulse.android.library)
    alias(libs.plugins.pulse.kotlin.compose)
}

android {
    namespace = "d31ruv.pulse.sutra.core.ui"
}

dependencies {
    api(libs.androidx.compose.material3)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.androidx.core.ktx)
}
