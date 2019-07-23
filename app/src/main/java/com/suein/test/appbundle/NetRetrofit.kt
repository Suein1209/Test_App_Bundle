package com.suein.test.appbundle

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit private constructor() {

    companion object {
        private val instatnce = NetRetrofit()
        fun instance() = instatnce
    }

    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://unsplash.it")
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build()
    }

    val service: UnsplashService

    init {
        service = getRetrofit().create(UnsplashService::class.java)
    }
}