package d31ruv.diecast.feature.profile

import d31ruv.diecast.core.data.profile.GithubProfileRepository
import d31ruv.diecast.core.data.profile.ProfileLookupResult
import d31ruv.diecast.core.data.profile.model.Profile
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun submitSearch_showsValidationForBlankQuery() = runTest {
        val viewModel = ProfileViewModel(FakeGithubProfileRepository())

        viewModel.submitSearch()

        assertEquals(
            "Enter a username to load a profile",
            viewModel.uiState.value.validationMessage,
        )
    }

    @Test
    fun submitSearch_emitsLoadingThenSuccess() = runTest {
        val repository = DeferredGithubProfileRepository()
        val viewModel = ProfileViewModel(repository)

        viewModel.onQueryChange("octocat")
        viewModel.submitSearch()
        assertEquals(ProfileScreenState.Loading, viewModel.uiState.value.screenState)

        repository.complete(ProfileLookupResult.Success(sampleProfile()))
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.screenState is ProfileScreenState.Success)
    }

    @Test
    fun submitSearch_emitsCachedFallbackState() = runTest {
        val repository = FakeGithubProfileRepository().apply {
            response = ProfileLookupResult.Success(
                sampleProfile(source = Profile.DataSource.Cache),
            )
        }
        val viewModel = ProfileViewModel(repository)

        viewModel.onQueryChange("octocat")
        viewModel.submitSearch()
        advanceUntilIdle()

        val successState = viewModel.uiState.value.screenState as ProfileScreenState.Success
        assertEquals(Profile.DataSource.Cache, successState.profile.source)
    }

    @Test
    fun submitSearch_emitsErrorState() = runTest {
        val repository = FakeGithubProfileRepository().apply {
            response = ProfileLookupResult.Error
        }
        val viewModel = ProfileViewModel(repository)

        viewModel.onQueryChange("octocat")
        viewModel.submitSearch()
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.screenState is ProfileScreenState.Error)
    }

    private class FakeGithubProfileRepository : GithubProfileRepository {
        var response: ProfileLookupResult = ProfileLookupResult.Error

        override suspend fun getProfile(username: String): ProfileLookupResult = response
    }

    private class DeferredGithubProfileRepository : GithubProfileRepository {
        private val response = CompletableDeferred<ProfileLookupResult>()

        override suspend fun getProfile(username: String): ProfileLookupResult = response.await()

        fun complete(result: ProfileLookupResult) {
            response.complete(result)
        }
    }
}

internal fun sampleProfile(
    source: Profile.DataSource = Profile.DataSource.Remote,
) = Profile(
    username = "octocat",
    displayName = "The Octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231",
    bio = "Mascot account",
    followers = 20,
    following = 0,
    publicRepos = 8,
    profileUrl = "https://github.com/octocat",
    cachedAtEpochMillis = 1_742_378_400_000,
    source = source,
)
