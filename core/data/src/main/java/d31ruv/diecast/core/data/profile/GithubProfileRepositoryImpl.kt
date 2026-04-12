package d31ruv.diecast.core.data.profile

import d31ruv.diecast.core.data.local.profile.GithubProfileDao
import d31ruv.diecast.core.data.local.profile.asExternalModel
import d31ruv.diecast.core.data.network.profile.GithubProfileApi
import d31ruv.diecast.core.data.network.profile.asEntity
import d31ruv.diecast.core.data.profile.model.Profile
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GithubProfileRepositoryImpl @Inject constructor(
    private val githubProfileApi: GithubProfileApi,
    private val githubProfileDao: GithubProfileDao,
) : GithubProfileRepository {
    override suspend fun getProfile(username: String): ProfileLookupResult {
        val normalizedUsername = username.trim().lowercase()
        val cachedProfile = githubProfileDao.getProfileByUsername(normalizedUsername)

        return try {
            val remoteProfile = githubProfileApi.getUser(normalizedUsername)
                .asEntity(fetchedAtEpochMillis = System.currentTimeMillis())
            githubProfileDao.upsertProfile(remoteProfile)
            ProfileLookupResult.Success(
                profile = remoteProfile.asExternalModel(source = Profile.DataSource.Remote),
            )
        } catch (error: Throwable) {
            when {
                cachedProfile != null -> ProfileLookupResult.Success(
                    profile = cachedProfile.asExternalModel(source = Profile.DataSource.Cache),
                )

                error is HttpException && error.code() == 404 -> ProfileLookupResult.NotFound
                error is IOException || error is HttpException -> ProfileLookupResult.Error
                else -> throw error
            }
        }
    }
}
