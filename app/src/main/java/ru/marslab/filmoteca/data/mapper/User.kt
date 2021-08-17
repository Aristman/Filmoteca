package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.domain.model.RequestToken

fun RequestTokenNW.toDomain(): RequestToken = RequestToken(
    this.requestToken,
    this.expiresAt
)