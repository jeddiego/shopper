package com.shopper.quiz.di.modules

import android.content.Context
import androidx.room.Room
import com.shopper.quiz.datasource.db.AppDatabase
import com.shopper.quiz.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.Db.DB_NAME
        )
            .build()
    }
}