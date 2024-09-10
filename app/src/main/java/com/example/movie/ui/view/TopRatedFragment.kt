package com.example.movie.ui.view

import TopRatedAdapter
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movie.R

import com.example.movie.databinding.FragmentTopRatedBinding
import com.example.movie.ui.viewmodel.TopRatedViewModel
import kotlinx.coroutines.launch

class TopRatedFragment : Fragment() {

    private val topRatedViewModel: TopRatedViewModel by viewModels()
    private lateinit var binding: FragmentTopRatedBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topRatedAdapter = TopRatedAdapter { selectedMovie ->
            fetchMovieDetailsAndNavigate(selectedMovie.id)
        }

        binding.topRatedRecyclerId.adapter = topRatedAdapter

        topRatedViewModel.topRatedMovies.observe(viewLifecycleOwner) { topRated ->
            topRatedAdapter.submitList(topRated)
        }
    }

    private fun fetchMovieDetailsAndNavigate(movieId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            if (movieId > 0) {
                val action = HomePageFragmentDirections.actionHomeItemIDToDetailFragId(movieId)
                findNavController().navigate(action)
            } else {
                Log.d(TAG, "fetchMovieDetailsAndNavigate: Invalid movieId: $movieId ")
            }
        }
    }
}
