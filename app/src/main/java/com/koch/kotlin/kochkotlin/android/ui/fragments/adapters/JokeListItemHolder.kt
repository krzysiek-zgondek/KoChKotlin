package com.koch.kotlin.kochkotlin.android.ui.fragments.adapters

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import com.koch.kotlin.kochkotlin.R
import com.koch.kotlin.kochkotlin.domain.model.Joke

class JokeListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val emptyCategory = view.context.getString(R.string.empty_category)

    lateinit var id: TextView
    lateinit var content: TextView
    lateinit var category: TextView

    fun bind(item: Joke) {
        id.text = "${item.id}"
        content.text = item.joke.decode()
        category.text = item.categories.firstOrNull() ?: emptyCategory
    }
}

fun String.decode(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)

    @Suppress("DEPRECATION")
    return Html.fromHtml(this)
}