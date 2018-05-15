package com.koch.kotlin.kochkotlin.domain.providers.events

import com.koch.kotlin.kochkotlin.domain.model.User
import com.koch.kotlin.kochkotlin.domain.providers.Signal
import java.io.IOException

class SignalNetworkError(val value: Throwable = IOException("Bad response")) : Signal {
    override fun signal() = NetworkErrorEvent(value)
}

class SignalProfileChanged(val value: User?) : Signal {
    override fun signal() = ProfileChangedEvent(value)
}