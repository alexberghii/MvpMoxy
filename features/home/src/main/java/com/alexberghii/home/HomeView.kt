package com.alexberghii.home

import androidx.paging.PagedList
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.NetworkState
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip


interface HomeView : MvpView {

    @AddToEndSingle
    fun submitCats(cats: PagedList<CatItem>)

    @AddToEndSingle
    fun updateViews(networkState: NetworkState)

    @Skip
    fun showSuccessMessage()

    @OneExecution
    fun downloadImage(url: String)
}