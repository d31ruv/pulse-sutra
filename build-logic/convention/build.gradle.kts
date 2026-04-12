import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.diecast.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("kotlinCompose") {
            id = libs.plugins.diecast.kotlin.compose.get().pluginId
            implementationClass = "KotlinComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.diecast.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.diecast.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
    }
}