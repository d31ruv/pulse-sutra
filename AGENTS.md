# Repository Guidelines

## Project Structure & Module Organization

This is a multi-module Android project using Kotlin DSL and convention plugins.

- `app/`: Android application module (app entrypoint, top-level navigation host, app theme,
  manifest, resources).
- `core/common/`: Shared core library code (dispatchers, DI helpers, reusable Android/Kotlin
  utilities).
- `core/data/`: Shared data layer code (repositories, data models, network utilities, DI
  bindings).
- `core/designsystem/`: Shared design system (Material 3 theme, colors, typography, reusable 
  UI components).
- `feature/chant/`: Chant feature module (screen UI, feature navigation, ViewModel, feature-
  scoped Compose code).
- `build-logic/`: Custom Gradle convention plugins (`pulse.android.application`,
  `pulse.android.library`, `pulse.kotlin.compose`, `pulse.hilt`).
- `gradle/libs.versions.toml`: Central dependency and plugin version catalog.

Use package root `d31ruv.pulse.sutra...` and mirror folder paths with package names.
Keep feature UI and navigation inside the owning `feature/*` module whenever possible, and keep
`app/` focused on app-level wiring.

## Build, Test, and Development Commands

Run from repository root:

- `./gradlew assembleDebug`: Build debug APK and compile all modules.
- `./gradlew testDebugUnitTest`: Run JVM unit tests for debug variants.
- `./gradlew connectedDebugAndroidTest`: Run instrumentation/UI tests on a connected
  device/emulator.
- `./gradlew lint`: Run Android lint checks.
- `./gradlew :app:installDebug`: Install debug app on a connected device.

On Windows, use `gradlew.bat` instead of `./gradlew`.

## Coding Style & Naming Conventions

- Language: Kotlin (`.kt`) with Gradle Kotlin DSL (`.kts`).
- Indentation: 4 spaces; keep line wrapping and trailing commas consistent with existing code.
- Navigation: Prefer type-safe Compose Navigation using `kotlinx.serialization` route objects
  (`@Serializable`) instead of raw string routes.
- Modularization: New user-facing work should generally start in a dedicated `feature/*` module
  rather than being implemented directly inside `app/`.
- Follow Kotlin/Android naming:
    - Classes/objects/composables: `PascalCase`
    - Functions/variables: `camelCase`
    - Constants: `UPPER_SNAKE_CASE`
    - Resource files/IDs: `snake_case`
- Respect module resource prefixes in library modules (for example `:core:common` uses
  `core_common_...` and `:feature:chant` uses `feature_chant_...`).

## Testing Guidelines

- Unit tests: `src/test/...` using JUnit4.
- Instrumented tests: `src/androidTest/...` using AndroidX test + Espresso/Compose test APIs.
- Test files should end with `Test.kt` and describe behavior clearly (example:
  `MainActivityTest.kt`).
- Add/adjust tests for any new behavior in the owning module (`app`, `core/*`, or `feature/*`).

## Commit & Pull Request Guidelines

- Follow Conventional Commit style used in history: `feat:`, `chore:`, `refactor:` (example:
  `feat: add common dispatcher module`).
- Keep commits focused and atomic; avoid mixing refactors with feature behavior changes.
- PRs should include:
    - Clear summary of what changed and why
    - Linked issue/ticket (if applicable)
    - Test evidence (`testDebugUnitTest`, manual/device checks)
    - UI screenshots for visual changes in `app` or feature modules such as `feature/chant`
