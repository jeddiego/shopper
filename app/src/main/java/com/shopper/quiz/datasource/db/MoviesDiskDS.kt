package com.shopper.quiz.datasource.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shopper.quiz.models.Movies

@Dao
interface MoviesDiskDS {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Movies>)

    @Query("DELETE FROM movies")
    fun truncate()

    @Query("SELECT * FROM movies WHERE title like :name ORDER BY title")
    fun readMovies(name: String): LiveData<List<Movies>>
}