package com.example.movie.ui.view

import WatchListAdapter
import WatchlistViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movie.R
import com.example.movie.data.local.AppDatabase
import com.example.movie.data.repository.WatchListMovieRepository
import com.example.movie.databinding.WatchListFragmentBinding
import com.example.movie.ui.viewmodel.WatchlistViewModel

class WatchListFragment : Fragment() {

    private val watchlistViewModel: WatchlistViewModel by viewModels {
        WatchlistViewModelFactory(WatchListMovieRepository(AppDatabase.getInstance(requireContext()).movieDao()))
    }

    private lateinit var binding: WatchListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.watch_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner // Bind to lifecycle owner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WatchListAdapter()
        binding.watchListRecyclerView.adapter = adapter

        watchlistViewModel.movies.observe(viewLifecycleOwner) { movies ->
            Log.d("WatchListFragment", "Observed movies: $movies")
            adapter.submitList(movies)
        }

        // Fetch the list of movies
        watchlistViewModel.displayMovies()
    }
}
