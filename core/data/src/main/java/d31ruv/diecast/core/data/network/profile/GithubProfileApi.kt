package d31ruv.diecast.core.data.network.profile

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubProfileApi {
    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
    ): GithubProfileDto
}
