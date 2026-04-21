plugins {
    alias(libs.plugins.pulse.android.library)
    alias(libs.plugins.pulse.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "d31ruv.pulse.sutra.feature.settings"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
