package com.koch.kotlin.kochkotlin.android.providers

import com.koch.kotlin.kochkotlin.domain.providers.Signal
import com.koch.kotlin.kochkotlin.domain.providers.SignalProvider
import org.greenrobot.eventbus.EventBus

class SignalProviderAndroid : SignalProvider() {
    override fun provide(signal: Signal) = EventBus.getDefault().post(signal.signal())
}