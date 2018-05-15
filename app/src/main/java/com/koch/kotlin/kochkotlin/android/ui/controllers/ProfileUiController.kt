package com.koch.kotlin.kochkotlin.android.ui.controllers

import android.support.constraint.ConstraintSet
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.koch.kotlin.kochkotlin.R
import com.koch.kotlin.kochkotlin.android.ui.animations.popIf
import com.koch.kotlin.kochkotlin.android.ui.animations.squeeze
import com.koch.kotlin.kochkotlin.android.ui.buildInputField
import com.koch.kotlin.kochkotlin.android.ui.forceFocus
import com.koch.kotlin.kochkotlin.android.ui.fragments.ProfileFragment
import com.koch.kotlin.kochkotlin.android.ui.fragments.provide
import com.koch.kotlin.kochkotlin.android.ui.onEditorDone
import com.koch.kotlin.kochkotlin.android.ui.onTextChanged
import com.koch.kotlin.kochkotlin.domain.model.User
import com.koch.kotlin.kochkotlin.domain.model.isProfileCorrect
import com.koch.kotlin.kochkotlin.domain.providers.events.SignalProfileChanged
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.design.themedFloatingActionButton
import org.jetbrains.anko.sdk25.coroutines.onClick

class ProfileUiController : UiControllerAndroid<ProfileFragment>() {
    private val user = User()

    override fun construct(ui: AnkoContext<ProfileFragment>): View = ui.apply{
        constraintLayout {
            padding = dimen(R.dimen.padding_outside_normal)

            forceFocus()

            val title = themedTextView(R.string.hello, R.style.Text_Title) {
                id = View.generateViewId()
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(width = matchParent)

            val subTitle = themedTextView(R.string.reach_knowledge, R.style.Text_Medium) {
                id = View.generateViewId()
                gravity = Gravity.CENTER
                padding = dip(8)
            }.lparams(width = matchParent)

            val proceed = themedFloatingActionButton(R.style.Button_Proceed){
                id = View.generateViewId()
                onClick{
                    if (user.isProfileCorrect()){
                        it?.squeeze {
                            component provide SignalProfileChanged(user)
                        }
                    }else Toast.makeText(component.ctx, R.string.dont_lie, Toast.LENGTH_SHORT).show()
                }
            }

            val firstName = buildInputField(R.string.first_name_hint){
                onTextChanged{
                    user.firstName = it.toString()
                    proceed popIf user.isProfileCorrect()
                }
            }

            val lastName = buildInputField(R.string.last_name_hint){
                onTextChanged{
                    user.lastName = it.toString()
                    proceed popIf user.isProfileCorrect()
                }

                onEditorDone(returnValue = false){
                    proceed.performClick()
                }
            }

            applyConstraintSet {
                connect(
                        ConstraintSetBuilder.Side.START of title.id to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.END of title.id to ConstraintSetBuilder.Side.END of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.TOP of title.id to ConstraintSetBuilder.Side.TOP of ConstraintSet.PARENT_ID,

                        ConstraintSetBuilder.Side.START of subTitle.id to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.TOP of subTitle.id to ConstraintSetBuilder.Side.BOTTOM of title.id,

                        ConstraintSetBuilder.Side.START of firstName.id to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.TOP of firstName.id to ConstraintSetBuilder.Side.BOTTOM of subTitle.id margin dip(24),

                        ConstraintSetBuilder.Side.START of lastName.id to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.TOP of lastName.id to ConstraintSetBuilder.Side.BOTTOM of firstName.id,

                        ConstraintSetBuilder.Side.END of proceed.id to ConstraintSetBuilder.Side.END of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.START of proceed.id to ConstraintSetBuilder.Side.START of ConstraintSet.PARENT_ID,
                        ConstraintSetBuilder.Side.TOP of proceed.id to ConstraintSetBuilder.Side.BOTTOM of lastName.id margin dip(64)
                )
            }
        }
    }.view
}