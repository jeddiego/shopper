package com.shopper.quiz.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: Movies
)

@Parcelize
@Entity(tableName = "movies")
data class Movies (
    @NonNull
    @PrimaryKey()
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
): Parcelable, Comparable<Movies> {
    override fun compareTo(other: Movies): Int = this.title.compareTo(other.title)
}