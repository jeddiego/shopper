package com.shopper.quiz.di.components

import android.content.Context
import com.shopper.quiz.di.modules.UtilModule
import com.shopper.quiz.providers.AppExecutors
import com.shopper.quiz.providers.AppFeaturesProvider
import com.shopper.quiz.providers.ResourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilModule::class])
interface UtilComponent {

    val appContext: Context

    val appExecutors: AppExecutors

    val resourceProvider: ResourceProvider

    val appFeaturesProvider: AppFeaturesProvider
}