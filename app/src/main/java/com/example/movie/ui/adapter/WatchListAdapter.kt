import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.data.models.MovieWatched
import com.example.movie.databinding.SearchItemBinding

class WatchListAdapter : ListAdapter<MovieWatched, WatchListAdapter.MyViewHolder>(WatchListDiffCallback()) {

    class MyViewHolder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        Log.d("WatchListAdapter", "Binding movie: $movie") // Log movie data
        holder.binding.apply {
            movieNameId.text = movie.title
            rateNumId.text = movie.rating.toString()
            categoryNameId.text = movie.category
            dateNumId.text = movie.releaseDate

            Glide.with(ivMovie.context)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .into(ivMovie)
        }
    }
}

class WatchListDiffCallback : DiffUtil.ItemCallback<MovieWatched>() {
    override fun areItemsTheSame(oldItem: MovieWatched, newItem: MovieWatched): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: MovieWatched, newItem: MovieWatched): Boolean {
        return oldItem == newItem
    }
}
