package com.alexberghii.favorites.di

import com.alexberghii.core.database.cats.DbCatRepository
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.favorites.FavoritesFragment
import com.alexberghii.favorites.adapter.FavoritesAdapter
import com.alexberghii.favorites.interactor.FavoritesInteractor
import com.alexberghii.favorites.interactor.IFavoritesInteractor
import dagger.Module
import dagger.Provides


@Module
class FavoritesModule(private val fragment: FavoritesFragment) {

    @FeatureScope
    @Provides
    fun provideFavoritesInteractor(
        dbRepository: DbCatRepository,
    ): IFavoritesInteractor = FavoritesInteractor(dbRepository)

    @FeatureScope
    @Provides
    fun provideFavoritesAdapter() = FavoritesAdapter(fragment.presenterProvider.get()::saveImage)
}