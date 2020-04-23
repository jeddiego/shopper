package com.shopper.quiz.models

import com.google.gson.annotations.SerializedName

//TODO Use classes
data class MoviesResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: Movies
)

data class Movies (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val titile: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)