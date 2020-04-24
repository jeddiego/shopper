package com.shopper.quiz.datasource.web

import androidx.lifecycle.Observer
import com.shopper.quiz.di.components.DaggerFrameworkComponent
import com.shopper.quiz.rest.services.WebService
import com.shopper.quiz.rest.utils.ApiCallback
import javax.inject.Inject

class MoviesWebDS {
    @Inject
    lateinit var webService: WebService

    init {
        DaggerFrameworkComponent.builder().build().inject(this)
    }

    fun download(
        apiKey: String,
        page: Int,
        observer: Observer<Any>
    ) {
        webService.getMovies(apiKey, page).enqueue(ApiCallback(observer))
    }

}