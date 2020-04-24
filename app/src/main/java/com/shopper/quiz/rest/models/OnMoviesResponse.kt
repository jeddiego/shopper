package com.shopper.quiz.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shopper.quiz.models.Movies

data class OnMoviesResponse(
    @SerializedName("results")
    @Expose
    val movies: List<Movies>
)