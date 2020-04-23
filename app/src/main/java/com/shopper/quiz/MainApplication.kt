package com.shopper.quiz

import android.app.Application
import android.content.Context
import com.shopper.quiz.di.components.DaggerUtilComponent
import com.shopper.quiz.di.components.UtilComponent
import com.shopper.quiz.di.modules.ContextModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        utilComponent = DaggerUtilComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var utilComponent: UtilComponent

        /*	public static AppExecutors getAppExecutors() {
		return utilComponent.getAppExecutors();
	}

	public static ResourceProvider getResourceProvider() {
		return utilComponent.getResourceProvider();
	}

	public static AppFeaturesProvider getAppFeaturesProvider() {
		return utilComponent.getAppFeaturesProvider();
	}*/
        val appContext: Context
            get() = utilComponent.appContext
    }
}