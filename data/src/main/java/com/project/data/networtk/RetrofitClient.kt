package com.project.data.networtk

import com.project.data.remote.service.AccountService
import com.project.data.remote.service.PostService
import com.project.data.remote.service.ShortTermForecastService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val SHORT_TERM_FORECAST_BASE_URL =
        "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"
    const val SERVER_BASE_URL = "http://3.34.57.141:80"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val shortTermOkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ShorttermForcastInterceptor())
            .build()
    }

    private val shortTermRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SHORT_TERM_FORECAST_BASE_URL)
            .client(shortTermOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var serverOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(HeaderInterceptor())
        .build()



    private var serverRetrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .client(serverOkHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val shortTermForecastService: ShortTermForecastService by lazy {
        shortTermRetrofit.create(ShortTermForecastService::class.java)
    }

    val accountService: AccountService by lazy {
        serverRetrofit.create(AccountService::class.java)
    }

    val postService: PostService by lazy {
        serverRetrofit.create(PostService::class.java)
    }

    fun makeServerOkHttpClient(){
        serverOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .build()

        serverRetrofit = Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(serverOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}