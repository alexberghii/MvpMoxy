package com.alexberghii.home.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.FeatureScope
import com.alexberghii.home.HomeFragment
import dagger.Component


@FeatureScope
@Component(modules = [HomeModule::class], dependencies = [CoreComponent::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, homeModule: HomeModule): HomeComponent
    }
}