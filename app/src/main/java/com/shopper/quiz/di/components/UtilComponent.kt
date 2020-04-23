package com.shopper.quiz.di.components

import android.content.Context
import com.shopper.quiz.di.modules.UtilModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilModule::class])
interface UtilComponent {
    //	AppExecutors getAppExecutors();
    //	ResourceProvider getResourceProvider();
    val appContext: Context
    //	AppFeaturesProvider getAppFeaturesProvider();
}