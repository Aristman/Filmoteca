package ru.marslab.filmoteca.domain.model

data class TimeZone(
    val iso31661: String,
    val names: List<String>
) {
    override fun toString(): String {
        return this.names.first()
    }
}
