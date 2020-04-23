package com.shopper.quiz.di.modules

import com.shopper.quiz.di.modules.ContextModule
import dagger.Module

@Module(includes = [ContextModule::class])
class UtilModule {
/*	@Provides
	@Singleton
	public AppExecutors provideAppExecutors() {
		return new AppExecutors();
	}

	@Provides
	@Singleton
	public ResourceProvider providerResourceProvider(Context context) {
		return new ResourceProvider(context);
	}

	@Provides
	@Singleton
	public AppFeaturesProvider provideAppFeaturesProvider(Context context) {
		return new AppFeaturesProvider(context);
	}*/
}