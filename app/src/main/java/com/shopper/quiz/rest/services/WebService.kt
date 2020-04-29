package com.shopper.quiz.rest.services

import com.shopper.quiz.utils.Constants.Web.API_URL
import com.shopper.quiz.rest.models.OnMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET(API_URL + "movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<OnMoviesResponse>

    @GET(API_URL + "search/movie")
    fun getSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Call<OnMoviesResponse>
}