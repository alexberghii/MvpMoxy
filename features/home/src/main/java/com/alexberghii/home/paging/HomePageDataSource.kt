package com.alexberghii.home.paging

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.alexberghii.core.domain.mapper.CatItemListMapper
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.NetworkState
import com.alexberghii.core.network.response.Result
import com.alexberghii.home.interactor.IHomeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class HomePageDataSource(private val interactor: IHomeInteractor): PageKeyedDataSource<Int, CatItem>() {

    private val initialPage = 0
    private val itemsPerPage = 50

    val networkState = PublishSubject.create<NetworkState>()

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CatItem>
    ) {
        networkState.onNext(NetworkState.Loading)
        interactor.getCats(initialPage, itemsPerPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result ->
                if (result is Result.Success) {
                    val data = CatItemListMapper.map(result.value)
                    callback.onResult(data, null, initialPage + 1)
                    networkState.onNext(NetworkState.Success)
                } else {
                    networkState.onNext(NetworkState.Error)
                }
            }, { networkState.onNext(NetworkState.Error) })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatItem>) {
        interactor.getCats(params.key, itemsPerPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result ->
                if (result is Result.Success) {
                    val data = CatItemListMapper.map(result.value)
                    callback.onResult(data, params.key + 1)
                    networkState.onNext(NetworkState.Success)
                } else {
                    networkState.onNext(NetworkState.Error)
                }
            }, { networkState.onNext(NetworkState.Error) })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatItem>) {
    }
}