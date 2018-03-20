package com.welander.donutscore.network

import com.welander.donutscore.application.DonutScoreScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by welander on 2018-03-17.
 */
@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/"
    }

    @DonutScoreScope
    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY }).build()

    @DonutScoreScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @DonutScoreScope
    @Provides
    fun creditScoreService(retrofit: Retrofit): CreditScoreService = retrofit.create(CreditScoreService::class.java)

}