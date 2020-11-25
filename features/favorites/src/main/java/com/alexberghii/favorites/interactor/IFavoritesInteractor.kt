package com.alexberghii.favorites.interactor

import androidx.paging.PagedList
import com.alexberghii.core.domain.model.CatItem
import io.reactivex.Observable


interface IFavoritesInteractor {

    fun getDbCats(pageSize: Int): Observable<PagedList<CatItem>>
}