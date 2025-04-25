package com.app.vinilos_misw4203.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.AlbumItemBinding
import com.app.vinilos_misw4203.models.Album

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var albums: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
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
        holder.viewDataBinding.also {
            it.album = albums[position]

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

    override fun getItemCount(): Int {
        return albums.size
    }

    class HomeViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}