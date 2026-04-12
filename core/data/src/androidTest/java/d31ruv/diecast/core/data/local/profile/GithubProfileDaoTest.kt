package d31ruv.diecast.core.data.local.profile

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import d31ruv.diecast.core.data.local.database.DiecastDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubProfileDaoTest {
    private lateinit var database: DiecastDatabase
    private lateinit var dao: GithubProfileDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            DiecastDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.githubProfileDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun upsertProfile_savesAndReadsByUsername() = runTest {
        val profile = GithubProfileEntity(
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

        dao.upsertProfile(profile)

        val storedProfile = dao.getProfileByUsername("octocat")
        assertEquals(profile, storedProfile)
    }
}
