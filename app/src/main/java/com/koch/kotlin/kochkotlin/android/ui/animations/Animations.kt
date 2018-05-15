package com.koch.kotlin.kochkotlin.android.ui.animations

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.animation.BounceInterpolator

infix fun View.popIf(isPopped: Boolean) {
    val scale = if (isPopped) 1.5f else 1f
    val delta = context.dp(24f)

    visibility = if (isPopped) View.VISIBLE else View.INVISIBLE
    animate().setInterpolator(BounceInterpolator())
            .translationX((1f - scale) * delta)
            .translationY((scale - 1f) * delta)
            .scaleX(scale).scaleY(scale).start()
}

infix fun View.squeeze(endAction: () -> Unit) {
    animate().setInterpolator(BounceInterpolator())
            .scaleX(0f).scaleY(0f).setStartDelay(250).withEndAction(endAction).start()
}

fun Context.dp(value: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics
)