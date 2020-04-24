package com.shopper.quiz.di.components

import com.shopper.quiz.activities.MainActivity
import com.shopper.quiz.activities.MovieDetailActivity
import com.shopper.quiz.di.modules.ViewModelModule
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(view: MainActivity)
    fun inject(view: MovieDetailActivity)
}