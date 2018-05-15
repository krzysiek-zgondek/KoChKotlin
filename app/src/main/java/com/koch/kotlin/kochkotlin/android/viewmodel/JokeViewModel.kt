package com.koch.kotlin.kochkotlin.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.koch.kotlin.kochkotlin.domain.model.Joke
import com.koch.kotlin.kochkotlin.domain.repositories.JokeRepository
import com.koch.kotlin.kochkotlin.domain.repositories.UserRepository
import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.query.Query

class JokeViewModel constructor(
        private val jokeRepository: JokeRepository,
        private val userRepository: UserRepository) : ViewModel() {

    val data: LiveData<List<Joke>>
        get() = jokeRepository.getJokes(userRepository.profile).live()

    fun refreshJokes() {
        jokeRepository.requestMultipleJokes(userRepository.profile)
    }

    fun removeAndGetNewJoke(item: Joke) {
        jokeRepository.removeJoke(item)
        jokeRepository.requestSingleJoke(userRepository.profile)
    }
}

fun <T> Query<T>.live() = ObjectBoxLiveData<T>(this)

