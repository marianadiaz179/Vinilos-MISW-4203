package com.app.vinilos_misw4203.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.AlbumItemBinding
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.models.AlbumDiffCallback

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var albums: List<Album> = emptyList()

    fun updateAlbums(newAlbums: List<Album>) {
        val diffCallback = AlbumDiffCallback(albums, newAlbums)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        albums = newAlbums
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            HomeViewHolder.LAYOUT,
            parent,
            false
        )
        return HomeViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val album = albums[position]
        holder.viewDataBinding.also {
            it.album = album

            it.cardContainer.setOnClickListener {
                val context = holder.itemView.context
                Toast.makeText(
                    context,
                    "Esta funcionalidad aún no está lista",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getItemCount(): Int = albums.size

    class HomeViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}