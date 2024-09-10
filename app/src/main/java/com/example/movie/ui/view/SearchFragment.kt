package com.example.movie.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentSearchBinding
import com.example.movie.ui.adapter.MovieAdapter
import com.example.movie.ui.viewmodel.SearchViewModel
import com.example.movie.ui.viewmodel.SearchViewModelFactory
import com.example.movie.data.repository.MovieRepository

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val movieRepository by lazy {
        MovieRepository()  // No need for Context
    }

    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(movieRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView
        binding.searchRecyclerId.layoutManager = LinearLayoutManager(context)
        val adapter = MovieAdapter()
        binding.searchRecyclerId.adapter = adapter

        // Observe search results
        searchViewModel.searchResults.observe(viewLifecycleOwner) { results ->
            Log.d("SearchFragment", "Number of movies received: ${results.size}")
            adapter.submitList(results)
        }

        // Set up the SearchView
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        searchViewModel.searchMovies(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        searchViewModel.searchMovies(it)
                    } else {
                        // Clear the results if the query is empty
                        searchViewModel.clearResults()
                    }
                }
                return true
            }
        })

        // Set up the back navigation for the toolbar
        binding.toolbar.toolbarId.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
