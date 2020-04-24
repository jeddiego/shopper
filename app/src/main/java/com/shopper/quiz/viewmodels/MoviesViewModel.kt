package com.shopper.quiz.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shopper.quiz.models.Movies

class MoviesViewModel : ViewModel() {
    val movies = MutableLiveData<List<Movies>>()

    //TODO retrieve data from back
    init {
        movies.value = listOf(
            Movies(
                1,
                "Movie 1",
                "The best of the movie 1",
                "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                "y",
                7.0,
                15
            ),
            Movies(
                2,
                "Movie 2",
                "The best of the movie 2",
                "/gzlbb3yeVISpQ3REd3Ga1scWGTU.jpg",
                "y",
                6.0,
                16
            ),
            Movies(
                3,
                "Movie 3",
                "The best of the movie 3",
                "/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg",
                "y",
                9.0,
                17
            )
        )
    }
}