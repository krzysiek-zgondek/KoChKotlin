package com.koch.kotlin.kochkotlin.android.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.koch.kotlin.kochkotlin.R
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.imageView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.themedFrameLayout

//just for fun
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        themedFrameLayout(R.style.MATCH_SPACE) {
            imageView(R.drawable.splash).lparams { gravity = Gravity.CENTER }
        }
    }

    override fun onResume() = super.onResume().also {
        launch(UI){
            delay(1000)

            if(isActive){
                startActivity<MainActivity>()
                finish()
            }
        }
    }
}