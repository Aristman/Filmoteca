package ru.marslab.filmoteca.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.marslab.filmoteca.data.model.auth.GuestNewSessionNW
import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW

interface MovieApi {
    @GET ("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String
    ): Response<RequestTokenNW>

    @GET("authentication/guest_session/new")
    suspend fun createGuestSessionId(
        @Query("api_key") apiKey: String
    ): Response<GuestNewSessionNW>

    @GET("guest_session/{guest_session_id}/rated/movies")
    suspend fun getGuestRatedMovies(
        @Path("guest_session_id") sessionId: String,
        @Query("api_key") apiKey: String
    ): Response<GuestRatedMoviesNW>
}