package com.koch.kotlin.kochkotlin.domain.providers

import com.koch.kotlin.kochkotlin.domain.providers.events.Event

interface Signal{
    fun signal(): Event
}

abstract class SignalProvider{
    abstract fun provide(signal: Signal)
}

