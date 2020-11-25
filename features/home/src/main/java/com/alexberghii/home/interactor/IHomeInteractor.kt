package com.alexberghii.home.interactor
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.response.CatResponse
import com.alexberghii.core.network.response.Result
import com.alexberghii.home.paging.HomePageDataSourceFactory
import io.reactivex.Completable
import io.reactivex.Observable


interface IHomeInteractor {

    fun getCats(page: Int, itemsPerPage: Int): Observable<Result<List<CatResponse>>>

    fun saveCatToDb(catItem: CatItem): Completable

    val dataSourceFactory: HomePageDataSourceFactory
}