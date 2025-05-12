package com.app.vinilos_misw4203.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.AlbumItemBinding
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.models.AlbumDiffCallback
import com.app.vinilos_misw4203.ui.AlbumFragmentDirections

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<Album> = emptyList()
    set(value) {
        val diffResult = DiffUtil.calculateDiff(AlbumDiffCallback(field, value))
        field = value
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.viewDataBinding.also {
            it.album = album

            it.cardContainer.setOnClickListener { view ->
                val action = AlbumFragmentDirections
                    .actionAlbumFragmentToAlbumDetailFragment(album.albumId)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = albums.size

    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}