package com.project.data.networtk

import com.project.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ShorttermForcastInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url.newBuilder()
            .addQueryParameter("ServiceKey", BuildConfig.SHORT_TERM_FORECAST_KEY)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
