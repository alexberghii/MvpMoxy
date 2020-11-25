package com.alexberghii.home

import androidx.paging.RxPagedListBuilder
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.home.interactor.IHomeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject


class HomePresenter @Inject constructor(
    private val interactor: IHomeInteractor
) : MvpPresenter<HomeView>() {

    private val pageSize = 50

    private val networkState = interactor.dataSourceFactory.networkState
    private val data = RxPagedListBuilder(interactor.dataSourceFactory, pageSize).buildObservable()

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.add(networkState.observeOn(AndroidSchedulers.mainThread()).subscribe( { viewState.updateViews(it) }, {}))
        disposable.add(data.subscribe(viewState::submitCats))
    }

    fun saveImage(catItem: CatItem) {
        viewState.downloadImage(catItem.url) //For an unknown reason, some viewState calls do not work
    }

    fun addToFavorites(catItem: CatItem) {
        disposable.add(interactor.saveCatToDb(catItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.showSuccessMessage() }, {}))
    }

    fun refresh() {
        interactor.dataSourceFactory.refresh()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.clear()
    }
}