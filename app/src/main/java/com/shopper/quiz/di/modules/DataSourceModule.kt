package com.shopper.quiz.di.modules

import com.shopper.quiz.datasource.web.MoviesWebDS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DataSourceModule {

    @Singleton
    @Provides
    fun provideBucketsWebDS(): MoviesWebDS {
        return MoviesWebDS()
    }
}