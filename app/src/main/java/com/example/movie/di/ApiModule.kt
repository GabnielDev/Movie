package com.example.movie.di

import com.example.movie.services.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideMovie(retrofit: Retrofit) =
        retrofit.create(MovieServiceInstance::class.java)

    @Provides
    @Singleton
    fun provideTv(retrofit: Retrofit) =
        retrofit.create(TvServiceInstance::class.java)

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