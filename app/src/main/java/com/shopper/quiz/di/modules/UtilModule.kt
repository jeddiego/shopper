package com.shopper.quiz.di.modules

import android.content.Context
import com.shopper.quiz.providers.AppExecutors
import com.shopper.quiz.providers.AppFeaturesProvider
import com.shopper.quiz.providers.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class UtilModule {
    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Provides
    @Singleton
    fun providerResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideAppFeaturesProvider(context: Context): AppFeaturesProvider {
        return AppFeaturesProvider(context)
    }
}