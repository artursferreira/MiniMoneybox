package com.example.minimoneybox.di

import com.example.minimoneybox.BuildConfig
import com.example.minimoneybox.api.MoneyBoxApi
import com.example.minimoneybox.ui.login.LoginViewModel
import com.example.minimoneybox.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

}

val networkModule = module {

    factory { providesHttplogging() }
    factory { providesOkHttpClient(get()) }

    single { provideRetrofit(okHttpClient = get(), BASE_URL) }

    factory { createWebService<MoneyBoxApi>(retrofit = get()) }
}

val loginModule = module {
    viewModel { LoginViewModel() }
}

fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

fun providesHttplogging(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return interceptor
}

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)
