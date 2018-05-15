package com.koch.kotlin.kochkotlin.android.ui.fragments

import android.support.v4.app.Fragment
import com.koch.kotlin.kochkotlin.domain.providers.Signal
import com.koch.kotlin.kochkotlin.domain.providers.SignalProvider
import com.koch.kotlin.kochkotlin.presentation.controller.UiController
import org.jetbrains.anko.AnkoContext
import org.koin.android.ext.android.get

abstract class BaseFragment<T: UiController> : Fragment() {
    internal abstract val controller: T

    fun tag(): String = javaClass.simpleName

    override fun onStart() = super.onStart().also { controller.onStart() }
    override fun onStop() = super.onStop().also { controller.onStop() }
}

infix fun <T: BaseFragment<*>> AnkoContext<T>.provide(event: Signal) {
    owner.get<SignalProvider>().provide(event)
}
