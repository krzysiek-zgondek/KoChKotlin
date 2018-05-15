package com.koch.kotlin.kochkotlin.android.net

import com.koch.kotlin.kochkotlin.android.net.responses.Failed
import com.koch.kotlin.kochkotlin.android.net.responses.Response
import kotlinx.coroutines.experimental.Deferred
import java.io.IOException

suspend fun <T: Response<T>> Deferred<retrofit2.Response<T>>.obtain(): Response<T> {
    return try {
        with(await()) {
            body()?.takeIf { isSuccessful } ?: Failed()
        }
    } catch (error: IOException) {
        Failed()
    }
}