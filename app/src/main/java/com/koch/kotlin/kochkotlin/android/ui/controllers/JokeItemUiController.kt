package com.koch.kotlin.kochkotlin.android.ui.controllers

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.koch.kotlin.kochkotlin.R
import com.koch.kotlin.kochkotlin.android.ui.fragments.adapters.JokeListItemHolder
import org.jetbrains.anko.*

class JokeListItem(ui: AnkoContext<ViewGroup>) : UiControllerAndroid<ViewGroup>() {
    lateinit var holder: JokeListItemHolder

    init {
        createView(ui)
    }

    override fun construct(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            themedLinearLayout(R.style.MATCH_WIDTH){
                lparams(width = matchParent){
                    padding = dimen(R.dimen.padding_list_item)
                }

                val id = themedTextView(R.style.Text_Big)

                val content = themedTextView(R.style.Text_Medium).lparams {
                    width = matchParent
                    marginStart = dip(24)
                    bottomMargin = dip(8)
                }

                val category = themedTextView(R.style.Text_Small).lparams {
                    bottomMargin = dip(8)
                    gravity = Gravity.END
                }

                linearLayout {
                    backgroundResource = R.drawable.list_spacer_gradient
                }.lparams(dip(200), dip(1)){
                    gravity = Gravity.END
                }

                with(this){
                    holder = JokeListItemHolder(this)
                    holder.id = id
                    holder.content = content
                    holder.category = category
                }
            }
        }
    }
}