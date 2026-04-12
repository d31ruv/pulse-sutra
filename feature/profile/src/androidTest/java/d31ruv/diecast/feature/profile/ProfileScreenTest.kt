package d31ruv.diecast.feature.profile

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import d31ruv.diecast.core.data.profile.model.Profile
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun profileScreen_supportsSearchLoadingSuccessAndErrorRendering() {
        var query = ""
        var searchClicks = 0

        composeRule.setContent {
            ProfileScreen(
                uiState = ProfileUiState(query = query),
                isOffline = false,
                onQueryChange = { query = it },
                onSearch = { searchClicks += 1 },
            )
        }

        composeRule.onNodeWithTag("profile_query_field").performTextInput("octocat")
        composeRule.onNodeWithTag("profile_load_button").performClick()

        composeRule.runOnIdle {
            check(searchClicks == 1)
        }

        composeRule.setContent {
            ProfileScreen(
                uiState = ProfileUiState(
                    query = "octocat",
                    screenState = ProfileScreenState.Loading,
                ),
                isOffline = false,
                onQueryChange = {},
                onSearch = {},
            )
        }
        composeRule.onNodeWithTag("profile_loading").assertExists()

        composeRule.setContent {
            ProfileScreen(
                uiState = ProfileUiState(
                    query = "octocat",
                    screenState = ProfileScreenState.Success(
                        profile = sampleProfile(),
                    ),
                ),
                isOffline = false,
                onQueryChange = {},
                onSearch = {},
            )
        }
        composeRule.onNodeWithTag("profile_card").assertExists()
        composeRule.onNodeWithText("The Octocat").assertExists()

        composeRule.setContent {
            ProfileScreen(
                uiState = ProfileUiState(
                    query = "octocat",
                    screenState = ProfileScreenState.Error(
                        "We couldn't load that profile right now. Try again in a moment.",
                    ),
                ),
                isOffline = false,
                onQueryChange = {},
                onSearch = {},
            )
        }
        composeRule.onNodeWithText("Retry").assertExists()
    }
}

private fun sampleProfile() = Profile(
    username = "octocat",
    displayName = "The Octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231",
    bio = "Mascot account",
    followers = 20,
    following = 0,
    publicRepos = 8,
    profileUrl = "https://github.com/octocat",
    cachedAtEpochMillis = 1_742_378_400_000,
    source = Profile.DataSource.Remote,
)
