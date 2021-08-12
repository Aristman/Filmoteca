package ru.marslab.filmoteca.data.model.guest


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GuestRatedTvEpisodesNW(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvEpisod>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class TvEpisod(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_number")
        val episodeNumber: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("production_code")
        val productionCode: String?,
        @SerializedName("rating")
        val rating: Int,
        @SerializedName("season_number")
        val seasonNumber: Int,
        @SerializedName("show_id")
        val showId: Int,
        @SerializedName("still_path")
        val stillPath: String?,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}