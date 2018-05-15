package com.koch.kotlin.kochkotlin.android

import android.app.Application
import org.koin.android.ext.android.startKoin


//normally clean architecture demands that base objects are POJOs
//but for sake of simplicity and that objectbox is cross JVM
//i've decided to use it in the domain. It implements Observables anyway
//as feature built-in in Queries so there is no need for building
//unnecessary logic around this. This is why presentation layer is almost empty
//there is no interaction because of ViewModel (ARCH Android) and converters because of ObjectBox

//Also Rx is not a way to go on Android platform
//there is whole Android Architecture built by Google
//for it to build proper MVVM apps (Lifecycles, LiveData, etc)

class Core : Application() {
    override fun onCreate() = super.onCreate().also {
        startKoin(this, Dependencies.get())
    }
}
