package com.alexberghii.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexberghii.core.database.cats.DbCatDao
import com.alexberghii.core.database.cats.DbCatItem


@Database(
    entities = [DbCatItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun catDao(): DbCatDao
}