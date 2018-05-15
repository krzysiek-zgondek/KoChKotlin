package com.koch.kotlin.kochkotlin.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.koch.kotlin.kochkotlin.android.ui.controllers.FeedUiController
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class FeedFragment : BaseFragment<FeedUiController>() {
    override val controller by lazy { FeedUiController() }

    override fun onCreateView(_i: LayoutInflater, _c: ViewGroup?, _s: Bundle?)
            = controller.createView(AnkoContext.create(ctx, this))
}