package ru.marslab.filmoteca.data.model.account


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class AccountNW(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("include_adult")
    val includeAdult: Boolean,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
) {

    @kotlinx.serialization.Serializable
    data class Avatar(
        @SerializedName("gravatar")
        val gravatar: Gravatar
    ) {
        @Serializable
        data class Gravatar(
            @SerializedName("hash")
            val hash: String
        )
    }

}