package com.alexberghii.home.interactor

import com.alexberghii.core.database.cats.DbCatRepository
import com.alexberghii.core.domain.extensions.mapToDb
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.repository.CatsRepository
import com.alexberghii.home.paging.HomePageDataSourceFactory


class HomeInteractor(
    private val repository: CatsRepository,
    private val dbRepository: DbCatRepository,
    homePageDataSourceFactory: HomePageDataSourceFactory) : IHomeInteractor {

    override val dataSourceFactory = homePageDataSourceFactory

    override fun getCats(page: Int, itemsPerPage: Int) = repository.getCats(page, itemsPerPage)!!

    override fun saveCatToDb(catItem: CatItem) = dbRepository.insertCatItem(catItem.mapToDb())
}