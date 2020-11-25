package com.alexberghii.mvpmoxy.di

import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.scopes.AppScope
import com.alexberghii.mvpmoxy.App
import dagger.Component


@AppScope
@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    fun inject(application: App)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }
}