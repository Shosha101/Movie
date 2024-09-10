
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movie.data.models.ResultXXX
import com.example.movie.databinding.PopularItemBinding
import com.example.movie.databinding.TopRatedItemBinding

class PopularAdapter(
    private val clickListener: (ResultXXX) -> Unit
) : ListAdapter<ResultXXX, PopularAdapter.MyViewHolder>(PopularItemDiffCallback()) {

    class MyViewHolder(val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PopularItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

        fun bind(result: ResultXXX, clickListener: (ResultXXX) -> Unit) {
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

class PopularItemDiffCallback : DiffUtil.ItemCallback<ResultXXX>() {
    override fun areItemsTheSame(oldItem: ResultXXX, newItem: ResultXXX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultXXX, newItem: ResultXXX): Boolean {
        return oldItem == newItem
    }
}
