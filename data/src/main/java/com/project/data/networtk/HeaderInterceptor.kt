package com.project.data.networtk

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "HeaderInterceptor"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val newBuilder = original.newBuilder()

        // 헤더에 인증토큰 추가
        val token: String = NetworkSettings.getInstance().token
        Log.d(TAG, "intercept: ${token}")
        if (token.isNotEmpty()) {
            newBuilder.addHeader("Authorization", "Bearer $token")
        }
        val request = newBuilder.build()

        return chain.proceed(request)
    }
}