package com.koch.kotlin.kochkotlin.android.ui.fragments.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.koch.kotlin.kochkotlin.android.ui.controllers.JokeListItem
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.helpers.JokeListDiff
import com.koch.kotlin.kochkotlin.domain.model.Joke
import org.jetbrains.anko.AnkoContext

class JokeListAdapter : RecyclerView.Adapter<JokeListItemHolder>() {
    var jokes: List<Joke> = emptyList()
        set(items) = with(field){
            field = items
            update(this, items)
        }

    override fun getItemCount() = jokes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = JokeListItem(AnkoContext.create(parent.context, parent)).holder

    override fun onBindViewHolder(holder: JokeListItemHolder, position: Int) =
            holder.bind( jokes[holder.adapterPosition] )

    private fun update(old: List<Joke>, new: List<Joke>){
        val diff = JokeListDiff(old, new)
        val result = DiffUtil.calculateDiff(diff)

        result.dispatchUpdatesTo(this@JokeListAdapter)
    }
}
