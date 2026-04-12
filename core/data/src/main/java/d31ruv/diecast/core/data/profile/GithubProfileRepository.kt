package d31ruv.diecast.core.data.profile

import d31ruv.diecast.core.data.profile.model.Profile

interface GithubProfileRepository {
    suspend fun getProfile(username: String): ProfileLookupResult
}

sealed interface ProfileLookupResult {
    data class Success(
        val profile: Profile,
    ) : ProfileLookupResult

    data object NotFound : ProfileLookupResult

    data object Error : ProfileLookupResult
}
