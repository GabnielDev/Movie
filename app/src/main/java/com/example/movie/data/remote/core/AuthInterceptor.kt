package com.example.movie.data.remote.core

//import com.example.movie.external.utils.Constants.API_KEY
import com.example.movie.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val modified = original.url

        val modifiedUrl = modified.newBuilder()
//            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("language", "en-US")
            .build()
        val requestBuilder = original.newBuilder().url(modifiedUrl).build()
        return chain.proceed(requestBuilder)
    }

}