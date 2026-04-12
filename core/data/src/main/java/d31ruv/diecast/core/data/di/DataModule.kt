package d31ruv.diecast.core.data.di

import android.content.Context
import androidx.room.Room
import d31ruv.diecast.core.data.local.database.DiecastDatabase
import d31ruv.diecast.core.data.local.profile.GithubProfileDao
import d31ruv.diecast.core.data.network.profile.GithubProfileApi
import d31ruv.diecast.core.data.profile.GithubProfileRepository
import d31ruv.diecast.core.data.profile.GithubProfileRepositoryImpl
import d31ruv.diecast.core.data.utils.network.NetworkMonitor
import d31ruv.diecast.core.data.utils.network.NetworkMonitorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(impl: NetworkMonitorImpl): NetworkMonitor

    @Binds
    internal abstract fun bindsGithubProfileRepository(impl: GithubProfileRepositoryImpl): GithubProfileRepository

    companion object {
        private const val DATABASE_NAME = "diecast.db"
        private const val BASE_URL = "https://api.github.com/"

        @Provides
        @Singleton
        fun provideDiecastDatabase(
            @ApplicationContext context: Context,
        ): DiecastDatabase = Room.databaseBuilder(
            context,
            DiecastDatabase::class.java,
            DATABASE_NAME,
        ).build()

        @Provides
        fun provideGithubProfileDao(
            database: DiecastDatabase,
        ): GithubProfileDao = database.githubProfileDao()

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
        ): Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

        @Provides
        fun provideGithubProfileApi(
            retrofit: Retrofit
        ): GithubProfileApi = retrofit.create(GithubProfileApi::class.java)
    }
}