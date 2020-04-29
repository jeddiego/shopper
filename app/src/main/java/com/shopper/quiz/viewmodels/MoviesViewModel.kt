package com.shopper.quiz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shopper.quiz.di.components.DaggerRepositoryComponent
import com.shopper.quiz.domain.MoviesRepository
import com.shopper.quiz.models.Movies
import javax.inject.Inject

class MoviesViewModel : ViewModel() {
    @Inject
    lateinit var repository: MoviesRepository
    private val query = MutableLiveData<String>()
    private var onlyLocal = false
    private var findMovieOnline = false
    val movies: LiveData<List<Movies>> = Transformations.switchMap(
        query,
        ::temp
    )

    private fun temp(name: String) = repository.getMovies(name, onlyLocal, findMovieOnline)

    init {
        DaggerRepositoryComponent.builder()
            .build().inject(this)
        query.value = ""
    }

    fun searchMovieByName(name: String, onlyLocal: Boolean, findMovieOnline: Boolean) = apply {
        this.onlyLocal = onlyLocal
        this.findMovieOnline = findMovieOnline
        query.value = name
    }
}