package com.shopper.quiz.di.modules

import com.shopper.quiz.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(): MoviesRepository {
        return MoviesRepository()
    }
}