package com.example.movie.ui.view

import PopularAdapter
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
import com.example.movie.databinding.FragmentPopularBinding

import com.example.movie.ui.viewmodel.PopularViewModel
import kotlinx.coroutines.launch

class PopularFragment : Fragment() {

    private val popularViewModel: PopularViewModel by viewModels()
    private lateinit var binding: FragmentPopularBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularAdapter = PopularAdapter { selectedMovie ->
            fetchMovieDetailsAndNavigate(selectedMovie.id)
        }

        binding.popularRecyclerId.adapter = popularAdapter

        popularViewModel.populardMovies.observe(viewLifecycleOwner) { topRated ->
            popularAdapter.submitList(topRated)
        }
    }

    private fun fetchMovieDetailsAndNavigate(movieId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val action = HomePageFragmentDirections.actionHomeItemIDToDetailFragId(movieId)
            findNavController().navigate(action)
        }
    }
}
