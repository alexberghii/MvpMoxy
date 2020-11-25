package com.alexberghii.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexberghii.core.domain.model.CatItem
import com.alexberghii.favorites.R
import com.bumptech.glide.Glide


class FavoritesAdapter(
    private val onSaveClicked: (CatItem) -> Unit
) : PagedListAdapter<CatItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<CatItem>() {
    override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CatItem, newItem: CatItem) = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> getItem(position)?.let { holder.bindView(it) }
        }
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        private val buttonSave = itemView.findViewById<Button>(R.id.buttonSave)

        fun bindView(item: CatItem) {
            if (item.url.isNotEmpty()) {
                Glide.with(itemView.context).load(item.url).into(imageView)
            }

            buttonSave.setOnClickListener { onSaveClicked(item) }
        }
    }
}