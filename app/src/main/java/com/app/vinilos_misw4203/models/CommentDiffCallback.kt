package com.app.vinilos_misw4203.models

import androidx.recyclerview.widget.DiffUtil
import com.app.vinilos_misw4203.models.Comment

class CommentDiffCallback(
        private val oldList: List<Comment>,
        private val newList: List<Comment>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // Suponiendo que cada comentario tiene un ID único
            return oldList[oldItemPosition].albumId == newList[newItemPosition].albumId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }