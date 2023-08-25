package com.niyas.trivify.di

import com.niyas.trivify.data.remote.TrivifyApi
import com.niyas.trivify.repository.TrivifyRepository
import com.niyas.trivify.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTriviaRepository(
        api: TrivifyApi
    ) = TrivifyRepository(api)

    @Singleton
    @Provides
    fun provideTrivifyApi(): TrivifyApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TrivifyApi::class.java)
    }
}