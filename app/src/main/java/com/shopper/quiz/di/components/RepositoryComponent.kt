package com.shopper.quiz.di.components

import com.shopper.quiz.di.modules.RepositoryModule
import com.shopper.quiz.viewmodels.MoviesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(viewModel: MoviesViewModel?)
}