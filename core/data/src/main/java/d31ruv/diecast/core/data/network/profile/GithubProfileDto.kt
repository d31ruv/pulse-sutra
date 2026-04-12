package d31ruv.diecast.core.data.network.profile

import com.google.gson.annotations.SerializedName
import d31ruv.diecast.core.data.local.profile.GithubProfileEntity

data class GithubProfileDto(
    @SerializedName("login")
    val username: String,
    @SerializedName("name")
    val displayName: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("html_url")
    val profileUrl: String,
)

internal fun GithubProfileDto.asEntity(
    fetchedAtEpochMillis: Long,
): GithubProfileEntity = GithubProfileEntity(
    username = username.lowercase(),
    displayName = displayName,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    publicRepos = publicRepos,
    profileUrl = profileUrl,
    cachedAtEpochMillis = fetchedAtEpochMillis,
)
