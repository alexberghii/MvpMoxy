package com.alexberghii.favorites

import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.favorites.interactor.IFavoritesInteractor
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject


class FavoritesPresenter @Inject constructor(
    interactor: IFavoritesInteractor
) : MvpPresenter<FavoritesView>() {

    private val pageSize = 50

    private val data = interactor.getDbCats(pageSize)

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.add(data.subscribe(viewState::submitCats))
    }

    fun saveImage(catItem: CatItem) {
        viewState.downloadImage(catItem.url)
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.clear()
    }
}