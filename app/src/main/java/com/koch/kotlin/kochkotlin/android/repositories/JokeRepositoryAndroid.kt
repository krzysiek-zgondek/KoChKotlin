package com.koch.kotlin.kochkotlin.android.repositories

import com.koch.kotlin.kochkotlin.android.net.obtain
import com.koch.kotlin.kochkotlin.android.net.responses.Failed
import com.koch.kotlin.kochkotlin.android.net.responses.JokesResponse
import com.koch.kotlin.kochkotlin.android.net.services.JokeServices
import com.koch.kotlin.kochkotlin.domain.model.Joke
import com.koch.kotlin.kochkotlin.domain.model.User
import com.koch.kotlin.kochkotlin.domain.providers.SignalProvider
import com.koch.kotlin.kochkotlin.domain.providers.events.SignalNetworkError
import com.koch.kotlin.kochkotlin.domain.repositories.JokeRepository
import com.koch.kotlin.kochkotlin.presentation.BaseConfig
import io.objectbox.Box
import io.objectbox.query.Query
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class JokeRepositoryAndroid constructor(
        private val api: JokeServices,
        private val jokeBox: Box<Joke> ) : JokeRepository, KoinComponent {

    private val signalProvider by inject<SignalProvider>()

    override fun getJokes(profile: User?): Query<Joke> {
        return jokeBox.query().build().also { requestMultipleJokes(profile) }
    }

    override fun removeJoke(item: Joke)
            = jokeBox.remove(item)

    override fun requestSingleJoke(profile: User?){
        profile?.let { requestJokes(it, 1, false) }
    }

    override fun requestMultipleJokes(profile: User?){
        profile?.let { requestJokes(it, BaseConfig.Jokes.Quantity, true) }
    }

    private fun requestJokes(profile: User, quantity: Int, removeOld: Boolean) = launch(UI) {
        val request = api.requestJokes(quantity, profile.firstName, profile.lastName).obtain()
        when(request){
            is Failed -> signalProvider.provide(SignalNetworkError())
            is JokesResponse -> with(jokeBox){
                if (removeOld)
                    removeAll()

                put(request.value)
            }
        }
    }
}