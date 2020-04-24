package com.shopper.quiz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shopper.quiz.di.components.DaggerRepositoryComponent
import com.shopper.quiz.domain.MoviesRepository
import com.shopper.quiz.models.Movies
import javax.inject.Inject

class MoviesViewModel : ViewModel() {
    @Inject
    lateinit var repository: MoviesRepository

    private var _movies = MutableLiveData<List<Movies>>()
    fun getMovies(): LiveData<List<Movies>> = _movies

    init {
        DaggerRepositoryComponent.builder()
            .build().inject(this)
        downloadMovies()
    }

    private fun downloadMovies() {
        _movies = repository.getMovies()
    }
}