package com.app.vinilos_misw4203.models

import androidx.recyclerview.widget.DiffUtil
import com.app.vinilos_misw4203.models.Album

class AlbumDiffCallback(
    private val oldList: List<Album>,
    private val newList: List<Album>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Asume que albumId es único
        return oldList[oldItemPosition].albumId == newList[newItemPosition].albumId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Compara todos los campos relevantes
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}