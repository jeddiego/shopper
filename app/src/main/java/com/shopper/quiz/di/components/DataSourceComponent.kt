package com.shopper.quiz.di.components

import com.shopper.quiz.di.modules.DataSourceModule
import com.shopper.quiz.domain.MoviesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataSourceModule::class])
interface DataSourceComponent {
    fun inject(bucketsRepository: MoviesRepository)
}