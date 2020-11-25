package com.alexberghii.home

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexberghii.commons.AndroidUtils
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.core.network.NetworkState
import com.alexberghii.favorites.FavoritesActivity
import com.alexberghii.home.adapter.HomeAdapter
import com.alexberghii.home.di.DaggerHomeComponent
import com.alexberghii.home.di.HomeModule
import kotlinx.android.synthetic.main.fragment_home.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Provider


class HomeFragment : MvpAppCompatFragment(), HomeView {

    @Inject
    lateinit var presenterProvider: Provider<HomePresenter>

    @Inject
    lateinit var adapter: HomeAdapter

    private val presenter: HomePresenter by moxyPresenter { presenterProvider.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (requireContext().applicationContext !is CoreComponentProvider) {
            throw IllegalStateException("Application class should implement CoreComponentProvider interface")
        }
        val coreComponent = (requireContext().applicationContext as CoreComponentProvider).provideCoreComponent()

        DaggerHomeComponent.factory().create(coreComponent, HomeModule(this)).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener { presenter.refresh() }
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_fav -> {
                navigateToFavorites()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun submitCats(cats: PagedList<CatItem>) {
        adapter.submitList(cats)
    }

    override fun updateViews(networkState: NetworkState) {
        swipeRefreshLayout.isRefreshing = false
        when (networkState) {
            NetworkState.Success -> {
                errorView.visibility = View.GONE
                loadingView.visibility = View.GONE
            }
            NetworkState.Loading -> {
                errorView.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
            }
            NetworkState.Error -> {
                errorView.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
            }
        }
    }

    override fun showSuccessMessage() {
        Toast.makeText(requireContext(), R.string.done, Toast.LENGTH_SHORT).show()
    }

    override fun downloadImage(url: String) {
        AndroidUtils.downloadImage(url, requireActivity())
    }

    private fun navigateToFavorites() {
        startActivity(Intent(requireContext(), FavoritesActivity::class.java))
    }
}