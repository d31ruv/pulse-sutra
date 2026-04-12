package d31ruv.diecast.core.data.profile.model

data class Profile(
    val username: String,
    val displayName: String?,
    val avatarUrl: String,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val publicRepos: Int,
    val profileUrl: String,
    val cachedAtEpochMillis: Long,
    val source: DataSource,
) {
    enum class DataSource {
        Remote,
        Cache,
    }
}
