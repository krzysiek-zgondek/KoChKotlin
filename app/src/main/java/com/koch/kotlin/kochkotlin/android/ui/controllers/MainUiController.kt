package com.koch.kotlin.kochkotlin.android.ui.controllers

import android.view.View
import com.koch.kotlin.kochkotlin.R
import com.koch.kotlin.kochkotlin.android.ui.activities.MainActivity
import com.koch.kotlin.kochkotlin.android.ui.activities.replaceFragment
import com.koch.kotlin.kochkotlin.android.ui.fragments.FeedFragment
import com.koch.kotlin.kochkotlin.android.ui.fragments.ProfileFragment
import com.koch.kotlin.kochkotlin.domain.providers.events.ProfileChangedEvent
import com.koch.kotlin.kochkotlin.domain.repositories.UserRepository
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.themedFrameLayout
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MainUiController : UiControllerAndroid<MainActivity>(), KoinComponent {
    private val userRepository: UserRepository by inject()

    private val containerId:Int = 1

    override fun onStart() = EventBus.getDefault().register(this).also{ routeFragments() }
    override fun onStop()  = EventBus.getDefault().unregister(this)

    override fun construct(ui: AnkoContext<MainActivity>): View = with(ui){
        themedFrameLayout(R.style.MATCH_SPACE) {
            id = containerId
        }
    }

    private fun routeFragments() = component.owner.replaceFragment(
            containerId, userRepository.profile?.let { FeedFragment() } ?: ProfileFragment()
    )

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: ProfileChangedEvent) = with(userRepository) {
        profile = event.profile

        routeFragments()
    }
}