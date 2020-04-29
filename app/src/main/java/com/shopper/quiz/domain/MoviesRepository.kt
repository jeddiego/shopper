package com.shopper.quiz.domain

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shopper.quiz.MainApplication
import com.shopper.quiz.R
import com.shopper.quiz.datasource.db.MoviesDiskDS
import com.shopper.quiz.datasource.web.MoviesWebDS
import com.shopper.quiz.di.components.DaggerDataSourceComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.models.Movies
import com.shopper.quiz.rest.models.OnMoviesResponse
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

    fun getMovies(
        name: String,
        onlyLocalSearch: Boolean,
        findMovieOnline: Boolean
    ): LiveData<List<Movies>> {
        when {
            findMovieOnline -> {
                return if (MainApplication.appFeaturesProvider.hasInternetConnection()) {
                    showMessage(R.string.searching_online)
                    getFromNetwork(name, findMovieOnline)
                } else {
                    showMessage(R.string.no_internet)
                    diskDS.readMovies(name)
                }
            }
            onlyLocalSearch -> {
                return diskDS.readMovies("%$name%")
            }
            MainApplication.appFeaturesProvider.hasInternetConnection() -> {
                return getFromNetwork(name, findMovieOnline)
            }
            else -> {
                showMessage(R.string.no_internet)
                return diskDS.readMovies("%$name%")
            }
        }
    }

    private fun showMessage(idMessage: Int) {
        Toast.makeText(
            MainApplication.appContext,
            MainApplication.appResourceProvider.getString(idMessage),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getFromNetwork(
        query: String,
        findMovieOnline: Boolean
    ): MutableLiveData<List<Movies>> {
        val movies = MutableLiveData<List<Movies>>()

        if (findMovieOnline) {
            webDS.search(API_KEY, query, Observer {
                if (it !is OnMoviesResponse) {
                    return@Observer
                }
                setupDiskDs(it.movies, findMovieOnline)
                movies.postValue(it.movies.sorted())
            })
        } else {
            webDS.download(API_KEY, page, Observer {
                if (it !is OnMoviesResponse) {
                    return@Observer
                }
                setupDiskDs(it.movies, findMovieOnline)
                movies.postValue(it.movies.sorted())
            })
        }
        return movies
    }

    private fun setupDiskDs(movies: List<Movies>, findMovieOnline: Boolean) {
        MainApplication.appExecutors.diskIO().execute {
            if (!findMovieOnline) {
                diskDS.truncate()
            }
            diskDS.insertAll(movies)
        }
    }
}