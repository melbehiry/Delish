package com.elbehiry.shared.data.network

import okhttp3.Interceptor
import okhttp3.Response

class CommonHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
            )
        }
}