package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.models.Result
import com.example.movie.databinding.ReviewItemBinding

class ReviewAdapter : ListAdapter<Result, ReviewAdapter.ReviewViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class ReviewViewHolder(private val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Result) {
            binding.authorId.text = review.author
            binding.commentTextId.text = review.content

            // Bind rating
            val rating = review.author_details.rating
            binding.reviewRatingId.text = rating?.toString() ?: "0.0"

            // Bind avatar
            val avatarPath = review.author_details.avatar_path
            if (avatarPath != null) {
                val avatarUrl = "https://image.tmdb.org/t/p/w500/$avatarPath"
                Glide.with(binding.root.context)
                    .load(avatarUrl)
                    .placeholder(R.drawable.placeholder) // Placeholder image
                    .error(R.drawable.placeholder) // Error image
                    .into(binding.reviewImageId)
            } else {
                // Set a default or placeholder image if avatarPath is null
                binding.reviewImageId.setImageResource(R.drawable.placeholder)
            }
        }
    }
}

class ReviewDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}
