package com.app.vinilos_misw4203.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.models.Comment
import com.app.vinilos_misw4203.models.CommentDiffCallback

class CommentsAdapter(private var comments: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionText: TextView = view.findViewById(R.id.comment_description)
        val ratingText: TextView = view.findViewById(R.id.comment_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.descriptionText.text = comment.description
        holder.ratingText.text = "Rating: ${comment.rating}"
    }

    override fun getItemCount() = comments.size

    fun updateComments(newComments: List<Comment>) {
        val diffCallback = CommentDiffCallback(comments, newComments)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        comments = newComments
        diffResult.dispatchUpdatesTo(this)
    }
}