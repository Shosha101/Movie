package com.example.movie.ui.view

import UpcomingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentUpcomingBinding


import com.example.movie.ui.viewmodel.UpComingViewModel
import kotlinx.coroutines.launch

class UpcomingFragment : Fragment() {

    private val upComingViewModel: UpComingViewModel by viewModels()
    private lateinit var binding: FragmentUpcomingBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val upComingAdapter = UpcomingAdapter { selectedMovie ->
            fetchMovieDetailsAndNavigate(selectedMovie.id)
        }

        binding.upComingRecyclerId.adapter = upComingAdapter

        upComingViewModel.upComingdMovies.observe(viewLifecycleOwner) { upComing ->
            upComingAdapter.submitList(upComing)
        }
    }

    private fun fetchMovieDetailsAndNavigate(movieId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val action = HomePageFragmentDirections.actionHomeItemIDToDetailFragId(movieId)
            findNavController().navigate(action)
        }
    }
}
