package com.koch.kotlin.kochkotlin.domain.repositories

import com.koch.kotlin.kochkotlin.domain.model.Joke
import com.koch.kotlin.kochkotlin.domain.model.User
import io.objectbox.query.Query

interface JokeRepository {

    fun getJokes(profile: User?): Query<Joke>

    fun removeJoke(item: Joke)

    fun requestMultipleJokes(profile: User?)

    fun requestSingleJoke(profile: User?)
}
