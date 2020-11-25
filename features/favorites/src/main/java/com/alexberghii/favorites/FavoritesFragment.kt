package com.alexberghii.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexberghii.commons.AndroidUtils
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.favorites.adapter.FavoritesAdapter
import com.alexberghii.favorites.di.DaggerFavoritesComponent
import com.alexberghii.favorites.di.FavoritesModule
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Provider


class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @Inject
    lateinit var presenterProvider: Provider<FavoritesPresenter>

    @Inject
    lateinit var adapter: FavoritesAdapter

    private val presenter: FavoritesPresenter by moxyPresenter { presenterProvider.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireContext().applicationContext !is CoreComponentProvider) {
            throw IllegalStateException("Application class should implement CoreComponentProvider interface")
        }
        val coreComponent = (requireContext().applicationContext as CoreComponentProvider).provideCoreComponent()

        DaggerFavoritesComponent.factory().create(coreComponent, FavoritesModule(this)).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun submitCats(cats: PagedList<CatItem>) {
        adapter.submitList(cats)
    }

    override fun downloadImage(url: String) {
        AndroidUtils.downloadImage(url, requireActivity())
    }
}