package com.shopper.quiz.di.modules

import com.shopper.quiz.datasource.db.AppDatabase
import com.shopper.quiz.datasource.db.MoviesDiskDS
import com.shopper.quiz.datasource.web.MoviesWebDS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesWebDS(): MoviesWebDS {
        return MoviesWebDS()
    }

    @Singleton
    @Provides
    fun provideMoviesDiskDS(database: AppDatabase): MoviesDiskDS {
        return database.moviesDiskDS
    }

}