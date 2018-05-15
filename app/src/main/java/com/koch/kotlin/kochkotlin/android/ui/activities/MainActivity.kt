package com.koch.kotlin.kochkotlin.android.ui.activities

import android.os.Bundle
import com.koch.kotlin.kochkotlin.android.ui.controllers.MainUiController
import com.koch.kotlin.kochkotlin.presentation.controller.UiController
import org.jetbrains.anko.setContentView

class MainActivity : BaseActivity<UiController>() {
    override val controller by lazy { MainUiController() }

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        controller.setContentView(this)
    }
}