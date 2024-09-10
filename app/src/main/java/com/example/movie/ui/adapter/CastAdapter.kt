package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.models.Crew
import com.example.movie.databinding.CrewItemBinding

class CastAdapter : ListAdapter<Crew, CastAdapter.CastViewHolder>(CastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = CrewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val crew = getItem(position)
        holder.bind(crew)
    }

    class CastViewHolder(private val binding: CrewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(crew: Crew) {
            binding.castNameId.text = crew.name

            val avatarPath = crew.profile_path
            if (avatarPath != null) {
                val avatarUrl = "https://image.tmdb.org/t/p/w500/$avatarPath"
                Glide.with(binding.root.context)
                    .load(avatarUrl)
                    .placeholder(R.drawable.placeholder) // Placeholder image
                    .error(R.drawable.placeholder) // Error image
                    .into(binding.castImageId)
            } else {
                // Set a default or placeholder image if avatarPath is null
                binding.castImageId.setImageResource(R.drawable.placeholder)
            }
        }
    }
}

class CastDiffCallback : DiffUtil.ItemCallback<Crew>() {
    override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem == newItem
    }
}
