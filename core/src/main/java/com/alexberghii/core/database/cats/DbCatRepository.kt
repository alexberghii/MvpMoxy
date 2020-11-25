package com.alexberghii.core.database.cats

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.alexberghii.core.mapper.Mapper
import io.reactivex.Observable


class DbCatRepository(private val dbCatDao: DbCatDao) {

    fun <T> getAllCatItems(mapper: Mapper<DbCatItem, T>, pageSize: Int = 50): Observable<PagedList<T>> {
        return RxPagedListBuilder(dbCatDao.getAllCatItemsDataSourceFactory().map(mapper::map), pageSize).buildObservable()
    }

    fun insertCatItem(catItem: DbCatItem) = dbCatDao.insertCatItem(catItem)
}