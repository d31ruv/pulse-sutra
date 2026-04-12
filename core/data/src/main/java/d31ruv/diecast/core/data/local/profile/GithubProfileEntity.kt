package d31ruv.diecast.core.data.local.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import d31ruv.diecast.core.data.profile.model.Profile

@Entity(tableName = "github_profiles")
data class GithubProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "display_name")
    val displayName: String?,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "bio")
    val bio: String?,
    @ColumnInfo(name = "followers")
    val followers: Int,
    @ColumnInfo(name = "following")
    val following: Int,
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,
    @ColumnInfo(name = "profile_url")
    val profileUrl: String,
    @ColumnInfo(name = "cached_at_epoch_millis")
    val cachedAtEpochMillis: Long,
)

internal fun GithubProfileEntity.asExternalModel(
    source: Profile.DataSource,
): Profile = Profile(
    username = username,
    displayName = displayName,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    publicRepos = publicRepos,
    profileUrl = profileUrl,
    cachedAtEpochMillis = cachedAtEpochMillis,
    source = source,
)
