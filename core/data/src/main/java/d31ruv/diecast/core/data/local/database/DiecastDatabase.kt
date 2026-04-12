package d31ruv.diecast.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import d31ruv.diecast.core.data.local.profile.GithubProfileDao
import d31ruv.diecast.core.data.local.profile.GithubProfileEntity

@Database(
    entities = [GithubProfileEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class DiecastDatabase : RoomDatabase() {
    abstract fun githubProfileDao(): GithubProfileDao
}