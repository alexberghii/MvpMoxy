package com.alexberghii.home.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.DataSource
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Provider


class HomePageDataSourceFactory @Inject constructor(
    private val dataSourceProvider: Provider<HomePageDataSource>
) : DataSource.Factory<Int, CatItem>() {

    val networkState = PublishSubject.create<NetworkState>()
    private lateinit var dataSource: HomePageDataSource

    @SuppressLint("CheckResult")
    override fun create(): DataSource<Int, CatItem> {
        dataSource = dataSourceProvider.get()
        dataSource.networkState.observeOn(AndroidSchedulers.mainThread()).subscribe({ networkState.onNext(it) }, {}) //TODO add to CompositeDisposable
        return dataSource
    }

    fun refresh() {
        dataSource.invalidate()
    }
}