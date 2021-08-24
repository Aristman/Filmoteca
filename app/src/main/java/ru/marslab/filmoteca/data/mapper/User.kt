package ru.marslab.filmoteca.data.mapper

import android.annotation.SuppressLint
import ru.marslab.filmoteca.data.model.auth.GuestNewSessionNW
import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.domain.model.Session
import ru.marslab.filmoteca.domain.model.Token
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.util.toTime


@SuppressLint("NewApi", "SimpleDateFormat")
fun RequestTokenNW.toDomain(): Token = Token(
    requestToken,
    expiresAt.toTime(Constants.STRING_DATE_FORMAT) ?: 0
)

@SuppressLint("NewApi", "SimpleDateFormat")
fun GuestNewSessionNW.toDomain(): Session = Session(
    guestSessionId,
    expiresAt.toTime(Constants.STRING_DATE_FORMAT) ?: 0
)