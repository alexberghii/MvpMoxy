package com.alexberghii.core.database.cats

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable


@Dao
interface DbCatDao {

    @Query("SELECT * FROM cat_items")
    fun getAllCatItemsDataSourceFactory(): DataSource.Factory<Int, DbCatItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatItem(catItem: DbCatItem): Completable
}