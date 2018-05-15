package com.koch.kotlin.kochkotlin.android.net.responses

import com.koch.kotlin.kochkotlin.domain.model.Joke

const val NO_TYPE = ""

//unfortunately sealed class have to be declared altogether
//which looks ugly sometimes
sealed class Response<T>
class Failed<T>: Response<T>()

data class JokesResponse(
        val type: String = NO_TYPE,
        val value: List<Joke> = emptyList()
) : Response<JokesResponse>()
