package com.app.vinilos_misw4203.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.vinilos_misw4203.R
import com.app.vinilos_misw4203.databinding.PerformerItemBinding
import com.app.vinilos_misw4203.models.Performer

class PerformersAdapter : RecyclerView.Adapter<PerformersAdapter.PerformerViewHolder>() {

    var performers: List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val binding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformerViewHolder.LAYOUT,
            parent,
            false
        )
        return PerformerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        holder.binding.performer = performers[position]
    }

    override fun getItemCount() = performers.size

    class PerformerViewHolder(val binding: PerformerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes val LAYOUT = R.layout.performer_item
        }
    }
}