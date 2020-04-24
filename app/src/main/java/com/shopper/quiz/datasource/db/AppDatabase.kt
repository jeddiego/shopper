package com.shopper.quiz.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shopper.quiz.models.Movies

@Database(entities = [Movies::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val moviesDiskDS: MoviesDiskDS

}