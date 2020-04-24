package com.shopper.quiz

import android.app.Application
import android.content.Context
import com.shopper.quiz.di.components.DaggerUtilComponent
import com.shopper.quiz.di.components.UtilComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.providers.AppExecutors
import com.shopper.quiz.providers.AppFeaturesProvider
import com.shopper.quiz.providers.ResourceProvider

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        utilComponent = DaggerUtilComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var utilComponent: UtilComponent

        val appContext: Context
            get() = utilComponent.appContext

        val appResourceProvider: ResourceProvider
            get() = utilComponent.resourceProvider

        val appFeaturesProvider: AppFeaturesProvider
            get() = utilComponent.appFeaturesProvider

        val appExecutors: AppExecutors
            get() = utilComponent.appExecutors
    }
}