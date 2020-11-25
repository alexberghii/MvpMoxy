package com.alexberghii.favorites

import androidx.paging.PagedList
import com.alexberghii.core.domain.model.CatItem
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution


interface FavoritesView : MvpView {

    @AddToEndSingle
    fun submitCats(cats: PagedList<CatItem>)

    @OneExecution
    fun downloadImage(url: String)
}