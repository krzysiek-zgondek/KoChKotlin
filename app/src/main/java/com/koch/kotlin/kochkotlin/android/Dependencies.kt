package com.koch.kotlin.kochkotlin.android

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.koch.kotlin.kochkotlin.android.net.services.JokeServices
import com.koch.kotlin.kochkotlin.android.providers.SignalProviderAndroid
import com.koch.kotlin.kochkotlin.android.repositories.JokeRepositoryAndroid
import com.koch.kotlin.kochkotlin.android.repositories.UserRepositoryAndroid
import com.koch.kotlin.kochkotlin.android.viewmodel.JokeViewModel
import com.koch.kotlin.kochkotlin.domain.model.Joke
import com.koch.kotlin.kochkotlin.domain.model.MyObjectBox
import com.koch.kotlin.kochkotlin.domain.model.User
import com.koch.kotlin.kochkotlin.domain.providers.SignalProvider
import com.koch.kotlin.kochkotlin.domain.repositories.JokeRepository
import com.koch.kotlin.kochkotlin.domain.repositories.UserRepository
import io.objectbox.Box
import io.objectbox.BoxStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies{
    fun get() = listOf(
            CommonModule, NetworkModule, StorageModule, ViewModelModule
    )

    private val NetworkModule: Module = applicationContext{
        bean{ get<Retrofit>().create(JokeServices::class.java) }

        factory {
            Retrofit.Builder().baseUrl(JokeServices.Config.url)
                    .addConverterFactory(get<GsonConverterFactory>())
                    .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
                    .client(get())
                    .build()
        }

        factory { OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }
        factory { with(HttpLoggingInterceptor()){setLevel(Level.BODY)} }
    }

    private val CommonModule: Module = applicationContext{
        factory { GsonBuilder().create() }
        factory { GsonConverterFactory.create(get()) }
        factory { CoroutineCallAdapterFactory() }

        factory { SignalProviderAndroid() as SignalProvider }
    }

    private val StorageModule: Module = applicationContext {
        bean { MyObjectBox.builder().androidContext(androidApplication()).build() }

        bean { UserRepositoryAndroid( get("userBox") ) as UserRepository }
        bean { JokeRepositoryAndroid( get(), get("jokeBox") ) as JokeRepository }

        factory("userBox") { get<BoxStore>().boxFor(User::class.java) as Box<User> }
        factory("jokeBox") { get<BoxStore>().boxFor(Joke::class.java) as Box<Joke> }
    }

    private val ViewModelModule: Module = applicationContext {
        viewModel { JokeViewModel(get(), get()) }
    }
}

