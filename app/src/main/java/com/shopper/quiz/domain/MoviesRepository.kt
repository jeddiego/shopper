package com.shopper.quiz.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shopper.quiz.MainApplication
import com.shopper.quiz.datasource.web.MoviesWebDS
import com.shopper.quiz.di.components.DaggerDataSourceComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.models.Movies
import com.shopper.quiz.rest.constants.Constants.Web.API_KEY
import com.shopper.quiz.rest.models.OnMoviesResponse
import com.shopper.quiz.rest.models.OnResponse
import javax.inject.Inject

class MoviesRepository {
    private val page = 1

    @Inject
    lateinit var webDS: MoviesWebDS

    init {
        DaggerDataSourceComponent.builder()
            .contextModule(ContextModule(MainApplication.appContext)).build().inject(this)
    }

    fun getMovies(): MutableLiveData<List<Movies>> = getFromNetwork()

    private fun getFromNetwork(): MutableLiveData<List<Movies>> {
        val movies = MutableLiveData<List<Movies>>()

        webDS.download(API_KEY, page, Observer {
            //FIXME observers
            if (it !is OnMoviesResponse) {
                val rawRespone: String = (it as OnResponse).message
//                observer.onChanged(rawRespone)
                return@Observer
            }

//            observer.onChanged(true)
            movies.postValue(it.movies)
        })

        return movies
    }

}