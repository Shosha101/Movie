
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movie.data.models.ResultX

import com.example.movie.databinding.UpcomingItemBinding

class UpcomingAdapter(
    private val clickListener: (ResultX) -> Unit
) : ListAdapter<ResultX, UpcomingAdapter.MyViewHolder>(UpComingItemDiffCallback()) {

    class MyViewHolder(val binding: UpcomingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UpcomingItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

        fun bind(result: ResultX, clickListener: (ResultX) -> Unit) {
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val imageUrl = baseUrl + result.poster_path

            Glide.with(binding.ivMovie.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.ivMovie)

            // Set click listener
            binding.root.setOnClickListener {
                clickListener(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position) // Use getItem() to retrieve the current item
        holder.bind(movie, clickListener)
    }
}

class UpComingItemDiffCallback : DiffUtil.ItemCallback<ResultX>() {
    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem == newItem
    }
}
