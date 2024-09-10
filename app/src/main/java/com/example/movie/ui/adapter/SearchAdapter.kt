package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.models.ResultXXXX
import com.example.movie.databinding.SearchItemBinding

class MovieAdapter : ListAdapter<ResultXXXX, MovieAdapter.MovieViewHolder>(SearchDiffCallback()) {

    inner class MovieViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: ResultXXXX) {
            binding.movieNameId.text = movie.title ?: "Unknown Title"
            binding.rateNumId.text = movie.vote_average?.toString() ?: "N/A"
            binding.categoryNameId.text = getGenreNames(movie.genre_ids)
            binding.dateNumId.text = movie.release_date ?: "Unknown Date"

            val backdropPath = movie.backdrop_path?.let { "https://image.tmdb.org/t/p/w500$it" }
            Glide.with(binding.ivMovie.context)
                .load(backdropPath ?: R.drawable.coupon)  // Handle null backdrop_path
                .into(binding.ivMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Assuming this function exists to map genre IDs to names
    private fun getGenreNames(genreIds: List<Int>?): String {
        // Example implementation, assuming you have a map of genre IDs to names
        val genreMap = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            10402 to "Music",
            9648 to "Mystery",
            10752 to "War",
            37 to "Western",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "Tv MOvie",
            53 to "Thriller",
        ) // Example map
        return genreIds?.map { genreMap[it] ?: "Unknown" }?.joinToString(", ") ?: "N/A"
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<ResultXXXX>() {
    override fun areItemsTheSame(oldItem: ResultXXXX, newItem: ResultXXXX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultXXXX, newItem: ResultXXXX): Boolean {
        return oldItem == newItem
    }
}
