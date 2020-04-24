package com.shopper.quiz.di.modules

import com.shopper.quiz.rest.constants.Constants
import com.shopper.quiz.rest.services.WebService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpBuilder(logging: HttpLoggingInterceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.connectTimeout(
            Constants.Web.CONNECT_TIMEOUT.toLong(),
            TimeUnit.SECONDS
        )
        builder.writeTimeout(
            Constants.Web.WRITE_TIMEOUT.toLong(),
            TimeUnit.SECONDS
        )
        builder.readTimeout(
            Constants.Web.READ_TIMEOUT.toLong(),
            TimeUnit.SECONDS
        )
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Web.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
}