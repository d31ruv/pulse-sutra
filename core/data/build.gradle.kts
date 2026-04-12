plugins {
    alias(libs.plugins.diecast.android.library)
    alias(libs.plugins.diecast.hilt)
}

android {
    namespace = "d31ruv.diecast.core.data"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.retrofit.core)
    implementation(libs.squareup.retrofit.converter.gson)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}