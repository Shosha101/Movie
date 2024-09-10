import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.ui.view.AboutMovieFragment
import com.example.movie.ui.view.CastFragment
import com.example.movie.ui.view.ReviewsFragment

class DetailsViewPagerAdapter(
    fragment: Fragment,
    private val itemCount: Int,
    private val movieOverview: String,
    private val movieId: Int  // Pass the movieId
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = itemCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutMovieFragment.newInstance(movieOverview)
            1 -> ReviewsFragment.newInstance(movieId)  // Pass movieId to ReviewsFragment
            2 -> CastFragment.castNewInstance(movieId)      // Pass movieId to CastFragment (if needed)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
