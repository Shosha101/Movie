package com.example.movie.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentReviewsBinding
import com.example.movie.ui.adapter.ReviewAdapter
import com.example.movie.ui.viewmodel.ReviewViewModel
import com.example.movie.ui.viewmodel.ReviewViewModelFactory

class ReviewsFragment : Fragment() {

    private var movieId: Int? = null
    private lateinit var binding: FragmentReviewsBinding
    private lateinit var reviewAdapter: ReviewAdapter
    private val viewModel: ReviewViewModel by viewModels { ReviewViewModelFactory() }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): ReviewsFragment {
            val fragment = ReviewsFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, movieId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(ARG_MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Observe the LiveData for reviews
        viewModel.reviews.observe(viewLifecycleOwner) { reviews ->
            if (reviews != null) {
                Log.d("ReviewsFragment", "Fetched reviews: ${reviews.size}")
                reviewAdapter.submitList(reviews)
            }
        }

        // Fetch reviews for the movie ID
        movieId?.let {
            viewModel.loadReviews(it)
        }
    }

    private fun setupRecyclerView() {
        reviewAdapter = ReviewAdapter()
        binding.reviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }
    }
}
