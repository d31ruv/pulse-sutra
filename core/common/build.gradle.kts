plugins {
    alias(libs.plugins.pulse.android.library)
    alias(libs.plugins.pulse.hilt)
}

android {
    namespace = "d31ruv.pulse.sutra.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
