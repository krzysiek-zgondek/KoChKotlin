package com.koch.kotlin.kochkotlin.android.ui.activities

import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import com.koch.kotlin.kochkotlin.android.ui.fragments.BaseFragment
import com.koch.kotlin.kochkotlin.presentation.controller.UiController

abstract class BaseActivity<T: UiController> : AppCompatActivity(){
    internal abstract val controller: T

    override fun onStart() = super.onStart().also { controller.onStart() }
    override fun onStop() = super.onStop().also { controller.onStop() }
}

fun <T: BaseFragment<*>> BaseActivity<*>.replaceFragment(@IdRes containerId: Int, fragment: T) {
    with(supportFragmentManager.beginTransaction()) {
        setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        replace(containerId, fragment, fragment.tag())
        commit()
    }
}