package com.shopper.quiz.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.shopper.quiz.viewmodels.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class ViewModelModule {

    @Provides
    fun provideMoviesViewModel(fragmentActivity: FragmentActivity): MoviesViewModel {
        return ViewModelProviders.of(fragmentActivity).get(MoviesViewModel::class.java)
    }
}