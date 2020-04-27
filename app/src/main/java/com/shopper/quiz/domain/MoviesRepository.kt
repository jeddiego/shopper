package com.shopper.quiz.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shopper.quiz.MainApplication
import com.shopper.quiz.datasource.db.MoviesDiskDS
import com.shopper.quiz.datasource.web.MoviesWebDS
import com.shopper.quiz.di.components.DaggerDataSourceComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.models.Movies
import com.shopper.quiz.rest.models.OnMoviesResponse
import com.shopper.quiz.rest.models.OnResponse
import com.shopper.quiz.utils.Constants.Web.API_KEY
import javax.inject.Inject

class MoviesRepository {
    private val page = 1

    @Inject
    lateinit var webDS: MoviesWebDS

    @Inject
    lateinit var diskDS: MoviesDiskDS

    init {
        DaggerDataSourceComponent.builder()
            .contextModule(ContextModule(MainApplication.appContext)).build().inject(this)
    }

    fun getMovies(name: String): LiveData<List<Movies>> {
        return if (MainApplication.appFeaturesProvider.hasInternetConnection() && name.isEmpty()) {
            cleanData()
            getFromNetwork()
        } else {
            diskDS.readMovies(name)
        }
    }

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
            diskDS.insertAll(it.movies)
            movies.postValue(it.movies)
        })

        return movies
    }

    private fun cleanData() {
        MainApplication.appExecutors.diskIO().execute { diskDS.truncate() }
    }
}