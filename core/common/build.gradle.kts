plugins {
    alias(libs.plugins.diecast.android.library)
    alias(libs.plugins.diecast.hilt)
}

android {
    namespace = "d31ruv.diecast.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}