package ru.marslab.filmoteca.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class AccountNW(
    @SerialName("avatar")
    val avatar: Avatar,
    @SerialName("id")
    val id: Int,
    @SerialName("include_adult")
    val includeAdult: Boolean,
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String
) {

    @kotlinx.serialization.Serializable
    data class Avatar(
        @SerialName("gravatar")
        val gravatar: Gravatar
    ) {
        @Serializable
        data class Gravatar(
            @SerialName("hash")
            val hash: String
        )
    }

}