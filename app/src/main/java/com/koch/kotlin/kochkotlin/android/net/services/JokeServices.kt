package com.koch.kotlin.kochkotlin.android.net.services

import com.koch.kotlin.kochkotlin.android.net.responses.JokesResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response as RetroResponse

interface JokeServices{
    object Config{
        const val url = "https://api.icndb.com"
    }

    @GET("jokes/random/{count}")
    fun requestJokes(@Path("count") quantity: Int,
                     @Query("firstName") firstName: String,
                     @Query("lastName") lastName: String): Deferred<RetroResponse<JokesResponse>>
}


