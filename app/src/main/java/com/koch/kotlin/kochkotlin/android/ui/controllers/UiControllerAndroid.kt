package com.koch.kotlin.kochkotlin.android.ui.controllers

import android.view.View
import com.koch.kotlin.kochkotlin.presentation.controller.UiController
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

abstract class UiControllerAndroid<T>: UiController, AnkoComponent<T> {
    lateinit var component: AnkoContext<T>

    protected abstract fun construct(ui: AnkoContext<T>): View

    override fun createView(ui: AnkoContext<T>): View {
        component = ui

        return construct(ui)
    }
}
