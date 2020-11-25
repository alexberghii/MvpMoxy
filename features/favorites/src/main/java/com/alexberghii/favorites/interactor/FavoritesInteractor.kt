package com.alexberghii.favorites.interactor

import com.alexberghii.core.database.cats.DbCatRepository
import com.alexberghii.core.domain.mapper.DbCatItemToDomainMapper


class FavoritesInteractor(private val dbCatRepository: DbCatRepository) : IFavoritesInteractor {

    override fun getDbCats(pageSize: Int) = dbCatRepository.getAllCatItems(DbCatItemToDomainMapper, pageSize)
}