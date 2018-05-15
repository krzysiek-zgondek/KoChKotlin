package com.koch.kotlin.kochkotlin.domain.providers.events

import com.koch.kotlin.kochkotlin.domain.model.User

sealed class Event

data class NetworkErrorEvent(val error: Throwable): Event()

data class ProfileChangedEvent(val profile: User?): Event()

