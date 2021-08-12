package ru.marslab.filmoteca.data.model.guest


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuestRatedTvEpisodesNW(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<TvEpisod>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class TvEpisod(
        @SerialName("air_date")
        val airDate: String,
        @SerialName("episode_number")
        val episodeNumber: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("overview")
        val overview: String,
        @SerialName("production_code")
        val productionCode: String?,
        @SerialName("rating")
        val rating: Int,
        @SerialName("season_number")
        val seasonNumber: Int,
        @SerialName("show_id")
        val showId: Int,
        @SerialName("still_path")
        val stillPath: String?,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int
    )
}