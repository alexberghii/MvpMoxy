package com.alexberghii.core.di.modules

import android.content.Context
import androidx.room.Room
import com.alexberghii.core.database.AppDatabase
import com.alexberghii.core.database.cats.DbCatDao
import com.alexberghii.core.database.cats.DbCatRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()

    @Singleton
    @Provides
    fun provideCatDao(database: AppDatabase) = database.catDao()

    @Singleton
    @Provides
    fun provideCatsRepository(catDao: DbCatDao) = DbCatRepository(catDao)
}