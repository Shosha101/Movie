package com.example.movie.ui.view

import NowPlayingAdapter
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
import com.example.movie.databinding.FragmentNowPlayingBinding // Corrected
import com.example.movie.ui.viewmodel.NowPlyingViewModel
import kotlinx.coroutines.launch

class NowPlayingFragment : Fragment() {

    private val nowPlayingViewModel: NowPlyingViewModel by viewModels()
    private lateinit var binding: FragmentNowPlayingBinding // Corrected

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_playing, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nowPlayingAdapter = NowPlayingAdapter { selectedMovie ->
            fetchMovieDetailsAndNavigate(selectedMovie.id)
        }

        binding.nowPlayingRecyclerId.adapter = nowPlayingAdapter

        nowPlayingViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { nowPlaying ->
            nowPlayingAdapter.submitList(nowPlaying)
        }
    }

    private fun fetchMovieDetailsAndNavigate(movieId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val action = HomePageFragmentDirections.actionHomeItemIDToDetailFragId(movieId)
            findNavController().navigate(action)
        }
    }
}
