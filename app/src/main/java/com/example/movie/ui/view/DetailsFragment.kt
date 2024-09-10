package com.example.movie.ui.view

import DetailsViewPagerAdapter
import WatchlistViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.data.local.AppDatabase
import com.example.movie.data.models.MovieWatched
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import com.example.movie.data.repository.DetailsMovieRepository
import com.example.movie.data.repository.WatchListMovieRepository
import com.example.movie.databinding.FragmentDetailsBinding
import com.example.movie.ui.viewmodel.DetailsViewModel
import com.example.movie.ui.viewmodel.DetailsViewModelFactory
import com.example.movie.ui.viewmodel.WatchlistViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailsFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)
    }

    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(DetailsMovieRepository(movieApiService))
    }

    private val watchlistViewModel: WatchlistViewModel by viewModels {
        WatchlistViewModelFactory(
            WatchListMovieRepository(AppDatabase.getInstance(requireContext()).movieDao())
        )
    }

    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE
        binding.contentScrollView.visibility = View.GONE

        viewPager = binding.tabBarDetailsId.detailsViewPagerId
        tabLayout = binding.tabBarDetailsId.detailsTabLayoutId

        val movieId = args.movieId
        viewModel.loadMovieDetails(movieId)

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            if (movie == null) {
                findNavController().navigate(R.id.action_detailFrag_id_to_NotFoundFragment)
            } else {
                binding.progressBar.visibility = View.GONE
                binding.contentScrollView.visibility = View.VISIBLE

                val movieOverview = movie.overview
                val adapter = DetailsViewPagerAdapter(this, 3, movieOverview, movie.id)
                viewPager.adapter = adapter

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = when (position) {
                        0 -> getString(R.string.about_movie)
                        1 -> getString(R.string.reviews)
                        2 -> getString(R.string.cast)
                        else -> null
                    }
                }.attach()

                binding.apply {
                    Glide.with(this@DetailsFragment)
                        .load("$IMAGE_BASE_URL${movie.backdrop_path}")
                        .error(R.drawable.coupon)
                        .into(moviePoster)

                    Glide.with(this@DetailsFragment)
                        .load("$IMAGE_BASE_URL${movie.poster_path}")
                        .error(R.drawable.coupon)
                        .into(shapeableImageView)

                    detailsMovieNameId.text = movie.title
                    movieRatingText.text = movie.vote_average.toString()
                    movieDateText.text = movie.release_date
                    movieCategoryText.text = movie.genres.joinToString(", ") { it.name }
                }
            }
        }

        binding.toolbar.detailsToolbarId.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.detailsToolbarId.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_info -> {
                    val movie = viewModel.movie.value
                    if (movie == null) {
                        println("Movie is null!")
                        Snackbar.make(view, "Unable to add movie", Snackbar.LENGTH_SHORT).show()
                    } else {
                        val movieWatched = MovieWatched(
                            movieId = movie.id,
                            title = movie.title,
                            category = movie.genres.joinToString(", ") { it.name },
                            posterPath = movie.poster_path,
                            rating = movie.vote_average,
                            releaseDate = movie.release_date
                        )
                        watchlistViewModel.saveMovie(movieWatched)
                        Snackbar.make(view, "Movie added to watchlist", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }
    }
}
