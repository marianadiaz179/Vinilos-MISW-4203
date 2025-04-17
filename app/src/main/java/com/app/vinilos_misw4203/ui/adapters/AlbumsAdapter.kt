package com.app.vinilos_misw4203.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.AlbumItemBinding
import com.app.vinilos_misw4203.models.Album
import com.app.vinilos_misw4203.ui.fragments.AlbumFragmentDirections

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = albums[position]
        holder.binding.root.setOnClickListener {
            val action =
                AlbumFragmentDirections
                    .actionAlbumFragment2ToCommentsFragment2(albums[position].albumId)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = albums.size

    class AlbumViewHolder(val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes val LAYOUT = R.layout.album_item
        }
    }
}
