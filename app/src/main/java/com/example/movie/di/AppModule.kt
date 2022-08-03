package com.example.movie.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movie.BuildConfig
import com.example.movie.network.AuthInterceptor
import com.example.movie.remote.service.*
import com.example.movie.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    fun providesBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(if (BuildConfig.DEBUG) chuckerInterceptor else loggingInterceptor)
            .connectTimeout(240, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .readTimeout(240, TimeUnit.SECONDS)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    fun provideChucker(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMovie(retrofit: Retrofit) = retrofit.create(MovieServiceInstance::class.java)

    @Provides
    @Singleton
    fun provideTv(retrofit: Retrofit) = retrofit.create(TvServiceInstance::class.java)

    @Provides
    @Singleton
    fun provideDetailMovie(retrofit: Retrofit) =
        retrofit.create(DetailMovieServiceInstance::class.java)

    @Provides
    @Singleton
    fun provideSearch(retrofit: Retrofit) =
        retrofit.create(SearchServiceInstace::class.java)

    @Provides
    @Singleton
    fun providePeople(retrofit: Retrofit) =
        retrofit.create(PeopleServinceInstance::class.java)
}