package com.alexberghii.mvpmoxy

import android.app.Application
import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.di.DaggerCoreComponent
import com.alexberghii.mvpmoxy.di.DaggerAppComponent


class App : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent() = coreComponent

    override fun onCreate() {
        super.onCreate()

        initCoreDi()
        initAppDi()
    }

    private fun initCoreDi() {
        coreComponent = DaggerCoreComponent.factory().create(this)
    }

    private fun initAppDi() {
        DaggerAppComponent.factory().create(coreComponent).inject(this)
    }
}