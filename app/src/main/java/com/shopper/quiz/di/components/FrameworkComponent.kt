package com.shopper.quiz.di.components

import com.shopper.quiz.datasource.web.MoviesWebDS
import com.shopper.quiz.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface FrameworkComponent {

    fun inject(moviesWebDS: MoviesWebDS)

}