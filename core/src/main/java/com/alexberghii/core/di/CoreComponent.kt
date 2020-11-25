package com.alexberghii.core.di

import android.content.Context
import com.alexberghii.core.database.cats.DbCatRepository
import com.alexberghii.core.di.modules.DatabaseModule
import com.alexberghii.core.di.modules.NetworkModule
import com.alexberghii.core.network.repository.CatsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface CoreComponent {

    fun context(): Context

    fun catsRepository(): CatsRepository

    fun dbCatRepository(): DbCatRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): CoreComponent
    }
}