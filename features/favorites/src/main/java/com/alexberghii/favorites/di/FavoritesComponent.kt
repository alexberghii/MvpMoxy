package com.alexberghii.favorites.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.favorites.FavoritesFragment
import dagger.Component


@FeatureScope
@Component(modules = [FavoritesModule::class], dependencies = [CoreComponent::class])
interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, homeModule: FavoritesModule): FavoritesComponent
    }
}