package com.koch.kotlin.kochkotlin.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koch.kotlin.kochkotlin.android.ui.controllers.ProfileUiController
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ProfileFragment: BaseFragment<ProfileUiController>(){
    override val controller by lazy{ ProfileUiController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = controller.createView(AnkoContext.create(ctx, this))
}

