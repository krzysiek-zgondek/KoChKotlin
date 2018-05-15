package com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.helpers

import android.support.v7.util.DiffUtil
import com.koch.kotlin.kochkotlin.domain.model.Joke

class JokeListDiff(private val oldList: List<Joke>, private val newList: List<Joke>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].id == newList[newPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldJoke = oldList[oldItemPosition]
        val newJoke = newList[newItemPosition]

        return oldJoke.joke.equals(newJoke.joke)
    }
}
