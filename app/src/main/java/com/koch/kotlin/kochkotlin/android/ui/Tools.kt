package com.koch.kotlin.kochkotlin.android.ui

import android.support.annotation.StringRes
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.ViewManager
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import com.koch.kotlin.kochkotlin.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout._ConstraintLayout
import org.jetbrains.anko.design.themedTextInputEditText
import org.jetbrains.anko.design.themedTextInputLayout
import org.jetbrains.anko.sdk25.coroutines.onEditorAction
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

fun @AnkoViewDslMarker _ConstraintLayout.forceFocus() = linearLayout {
    id = View.generateViewId()
    isFocusableInTouchMode = true
}

fun @AnkoViewDslMarker TextInputEditText.onTextChanged(function: (CharSequence?) -> Unit)= textChangedListener {
    onTextChanged{ source, _, _, _ ->
        function(source)
    }
}

fun @AnkoViewDslMarker TextInputEditText.onEditorDone(returnValue: Boolean, function: () -> Unit){
    return onEditorAction(returnValue = returnValue){ _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE)
            function()
    }
}

inline fun ViewManager.buildInputField(@StringRes hint: Int, actions: @AnkoViewDslMarker TextInputEditText.() -> Unit): TextInputLayout {
    return themedTextInputLayout(R.style.MATCH_WIDTH) {
        id = View.generateViewId()

        themedTextInputEditText(R.style.MATCH_WIDTH) {
            hintResource = hint

            inputType = EditorInfo.TYPE_CLASS_TEXT
            maxLines = 1

            actions()
        }

        lparams(matchParent, wrapContent)
    }
}

fun @AnkoViewDslMarker _RelativeLayout.shadows(top: TextView, bottom: LinearLayout) {
    val up = linearLayout {backgroundResource = R.drawable.grey_v2_shadow_gradient}
    val down = linearLayout {backgroundResource = R.drawable.grey_v2_shadow_gradient_invert}

    up.lparams(width = matchParent, height = dip(2)){ below(top) }
    down.lparams(width = matchParent, height = dip(2)){ above(bottom) }
}
