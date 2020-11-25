package com.alexberghii.core.database.cats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cat_items")
data class DbCatItem(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "width") val width: Int
)