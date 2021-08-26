package ru.marslab.filmoteca.domain.model

data class Language(
    val iso6391: String,
    val name: String
) {
    override fun toString(): String {
        return this.name
    }
}
