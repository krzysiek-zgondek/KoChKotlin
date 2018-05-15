package com.koch.kotlin.kochkotlin.android.ui.controllers

import android.animation.LayoutTransition
import android.arch.lifecycle.Observer
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.koch.kotlin.kochkotlin.R
import com.koch.kotlin.kochkotlin.android.ui.fragments.FeedFragment
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.JokeListAdapter
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.helpers.JokeListItemCallback
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.helpers.itemActionCallback
import com.koch.kotlin.kochkotlin.android.ui.fragments.provide
import com.koch.kotlin.kochkotlin.android.ui.shadows
import com.koch.kotlin.kochkotlin.android.viewmodel.JokeViewModel
import com.koch.kotlin.kochkotlin.domain.model.Joke
import com.koch.kotlin.kochkotlin.domain.providers.events.NetworkErrorEvent
import com.koch.kotlin.kochkotlin.domain.providers.events.SignalProfileChanged
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.*
import org.jetbrains.anko.design.themedFloatingActionButton
import org.jetbrains.anko.recyclerview.v7.themedRecyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.themedSwipeRefreshLayout
import org.koin.android.architecture.ext.getViewModel

class FeedUiController : UiControllerAndroid<FeedFragment>() {
    private lateinit var jokeSwipeRefresh: SwipeRefreshLayout

    override fun onStart() = EventBus.getDefault().register(this)
    override fun onStop()  = EventBus.getDefault().unregister(this)

    override fun construct(ui: AnkoContext<FeedFragment>): View {
        val jokeListAdapter = JokeListAdapter()

        val jokeViewModel = ui.owner.getViewModel<JokeViewModel>()
        jokeViewModel.data.observe(ui.owner, Observer<List<Joke>> { jokes ->
            jokeListAdapter.jokes = jokes ?: emptyList()
            jokeSwipeRefresh.isRefreshing = jokes?.isEmpty() != false
        })

        return with(ui){
            relativeLayout {
                padding = dimen(R.dimen.padding_outside_normal)
                bottomPadding = 0

                val header = themedTextView(R.string.truth, R.style.Text_Medium) {
                    id = View.generateViewId()
                    gravity = Gravity.CENTER
                    padding = dip(8)
                }.lparams {
                    centerHorizontally()
                    bottomMargin = dip(8)
                }

                val menu = themedLinearLayout(R.style.MATCH_WIDTH) {
                    id = View.generateViewId()
                    gravity = Gravity.CENTER
                    layoutTransition = LayoutTransition()

                    themedFloatingActionButton(R.style.Button_Profile) {
                        onClick { provide(SignalProfileChanged(null)) }
                    }
                }.lparams( matchParent, dip(80)){
                    alignParentBottom()
                }

                shadows(header, menu)

                jokeSwipeRefresh = themedSwipeRefreshLayout(R.style.MATCH_SPACE) {
                    isRefreshing = true
                    onRefresh {
                        jokeViewModel.refreshJokes()
                    }

                    themedRecyclerView(R.style.MATCH_SPACE) {
                        adapter = jokeListAdapter
                        layoutManager = LinearLayoutManager(ctx)
                        itemActionCallback = JokeListItemCallback(jokeListAdapter, jokeViewModel)
                    }
                }.lparams(width = matchParent, height = matchParent) {
                    above(menu)
                    below(header)
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    internal fun onEventMainThread(event: NetworkErrorEvent) {
        jokeSwipeRefresh.isRefreshing = false
        Toast.makeText(component.ctx, R.string.huston_problem, Toast.LENGTH_SHORT).show()
    }
}