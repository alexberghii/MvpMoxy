package com.alexberghii.home.di

import com.alexberghii.core.database.cats.DbCatRepository
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.core.network.repository.CatsRepository
import com.alexberghii.home.HomeFragment
import com.alexberghii.home.adapter.HomeAdapter
import com.alexberghii.home.interactor.HomeInteractor
import com.alexberghii.home.interactor.IHomeInteractor
import com.alexberghii.home.paging.HomePageDataSource
import com.alexberghii.home.paging.HomePageDataSourceFactory
import dagger.Module
import dagger.Provides


@Module
class HomeModule(private val fragment: HomeFragment) {

    @FeatureScope
    @Provides
    fun provideHomeInteractor(
        repository: CatsRepository,
        dbRepository: DbCatRepository,
        dataSourceFactory: HomePageDataSourceFactory
    ): IHomeInteractor = HomeInteractor(repository, dbRepository, dataSourceFactory)

    @Provides
    fun provideHomePageDataSource(interactor: IHomeInteractor) = HomePageDataSource(interactor)

    @FeatureScope
    @Provides
    fun provideHomeAdapter() = HomeAdapter(fragment.presenterProvider.get()::addToFavorites, fragment.presenterProvider.get()::saveImage)
}