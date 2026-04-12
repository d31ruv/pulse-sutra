package d31ruv.diecast.core.data.profile

import d31ruv.diecast.core.data.local.profile.GithubProfileDao
import d31ruv.diecast.core.data.local.profile.GithubProfileEntity
import d31ruv.diecast.core.data.network.profile.GithubProfileApi
import d31ruv.diecast.core.data.network.profile.GithubProfileDto
import d31ruv.diecast.core.data.profile.model.Profile
import java.io.IOException
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class GithubProfileRepositoryImplTest {
    @Test
    fun getProfile_returnsRemoteProfileAndCachesIt() = runTest {
        val dao = FakeGithubProfileDao()
        val repository = GithubProfileRepositoryImpl(
            githubProfileApi = FakeGithubProfileApi(
                result = Result.success(sampleDto(username = "octocat")),
            ),
            githubProfileDao = dao,
        )

        val result = repository.getProfile("octocat")

        assertTrue(result is ProfileLookupResult.Success)
        result as ProfileLookupResult.Success
        assertEquals(Profile.DataSource.Remote, result.profile.source)
        assertEquals("octocat", result.profile.username)
        assertEquals("octocat", dao.storedProfiles["octocat"]?.username)
    }

    @Test
    fun getProfile_returnsCachedProfileWhenRemoteFails() = runTest {
        val dao = FakeGithubProfileDao().apply {
            storedProfiles["octocat"] = sampleEntity()
        }
        val repository = GithubProfileRepositoryImpl(
            githubProfileApi = FakeGithubProfileApi(
                result = Result.failure(IOException("offline")),
            ),
            githubProfileDao = dao,
        )

        val result = repository.getProfile("octocat")

        assertTrue(result is ProfileLookupResult.Success)
        result as ProfileLookupResult.Success
        assertEquals(Profile.DataSource.Cache, result.profile.source)
        assertEquals("The Octocat", result.profile.displayName)
    }

    @Test
    fun getProfile_returnsNotFoundWhenRemoteReturns404AndCacheMissing() = runTest {
        val repository = GithubProfileRepositoryImpl(
            githubProfileApi = FakeGithubProfileApi(
                result = Result.failure(
                    HttpException(Response.error<Any>(404, "".toResponseBody(null))),
                ),
            ),
            githubProfileDao = FakeGithubProfileDao(),
        )

        val result = repository.getProfile("missing-user")

        assertEquals(ProfileLookupResult.NotFound, result)
    }

    @Test
    fun getProfile_returnsErrorWhenRemoteFailsAndCacheMissing() = runTest {
        val repository = GithubProfileRepositoryImpl(
            githubProfileApi = FakeGithubProfileApi(
                result = Result.failure(IOException("network down")),
            ),
            githubProfileDao = FakeGithubProfileDao(),
        )

        val result = repository.getProfile("octocat")

        assertEquals(ProfileLookupResult.Error, result)
    }

    private class FakeGithubProfileApi(
        private val result: Result<GithubProfileDto>,
    ) : GithubProfileApi {
        override suspend fun getUser(username: String): GithubProfileDto = result.getOrThrow()
    }

    private class FakeGithubProfileDao : GithubProfileDao {
        val storedProfiles = linkedMapOf<String, GithubProfileEntity>()

        override suspend fun getProfileByUsername(username: String): GithubProfileEntity? {
            return storedProfiles[username]
        }

        override suspend fun upsertProfile(profile: GithubProfileEntity) {
            storedProfiles[profile.username] = profile
        }
    }
}

private fun sampleDto(username: String) = GithubProfileDto(
    username = username,
    displayName = "The Octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231",
    bio = "Mascot account",
    followers = 20,
    following = 0,
    publicRepos = 8,
    profileUrl = "https://github.com/$username",
)

private fun sampleEntity() = GithubProfileEntity(
    username = "octocat",
    displayName = "The Octocat",
    avatarUrl = "https://avatars.githubusercontent.com/u/583231",
    bio = "Mascot account",
    followers = 20,
    following = 0,
    publicRepos = 8,
    profileUrl = "https://github.com/octocat",
    cachedAtEpochMillis = 1_742_378_400_000,
)
