package com.alexberghii.core.network.interceptor

import com.alexberghii.core.network.AppService
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("x-api-key", AppService.API_KEY)
            .header("Cache-Control", "no-cache")
            .header("Connection", "close")
            .build()

        return chain.proceed(request)
    }
}