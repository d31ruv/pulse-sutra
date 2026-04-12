package d31ruv.diecast.core.data.local.profile

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface GithubProfileDao {
    @Query("SELECT * FROM github_profiles WHERE username = :username LIMIT 1")
    suspend fun getProfileByUsername(username: String): GithubProfileEntity?

    @Upsert
    suspend fun upsertProfile(profile: GithubProfileEntity)
}
