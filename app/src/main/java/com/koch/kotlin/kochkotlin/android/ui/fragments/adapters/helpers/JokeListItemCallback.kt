package com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.helpers

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.LEFT
import android.support.v7.widget.helper.ItemTouchHelper.RIGHT
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.JokeListAdapter
import com.koch.kotlin.kochkotlin.android.viewmodel.JokeViewModel
import org.jetbrains.anko.recyclerview.v7._RecyclerView

var _RecyclerView.itemActionCallback: JokeListItemCallback
    get() = throw NotImplementedError("property should only be used as setter")
    set(callback) = ItemTouchHelper(callback).attachToRecyclerView(this)

class JokeListItemCallback (
        private val adapter: JokeListAdapter,
        private val jokeViewModel: JokeViewModel)
    : ItemTouchHelper.SimpleCallback(0, LEFT or RIGHT) {

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int): Unit = with(adapter){
        jokes[viewHolder.adapterPosition].let {
            jokeViewModel.removeAndGetNewJoke(it)
        }
    }
}
