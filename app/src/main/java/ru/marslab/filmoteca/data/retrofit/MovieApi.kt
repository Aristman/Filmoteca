package ru.marslab.filmoteca.data.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.marslab.filmoteca.data.model.auth.GuestNewSessionNW
import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.data.model.auth.SessionNW
import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.data.model.tv.TvShowsNW

interface MovieApi {
    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String
    ): Response<RequestTokenNW>

    @FormUrlEncoded
    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") apiKey: String,
        @Field("request_token") requestToken: String
    ): Response<SessionNW>

    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    suspend fun createSessionWithLogin(
        @Query("api_key") apiKey: String,
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("request_token") requestToken: String
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

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MoviesNW>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String
    ): Response<TvShowsNW>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String
    ): Response<MoviesNW>
}