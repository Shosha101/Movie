package com.example.movie.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentCastBinding
import com.example.movie.ui.adapter.CastAdapter
import com.example.movie.ui.viewmodel.CastViewModel
import com.example.movie.ui.viewmodel.CastViewModelFactory


class CastFragment : Fragment() {

    private var movieId: Int? = null
    private lateinit var binding: FragmentCastBinding
    private lateinit var castAdapter: CastAdapter
    private val viewModel: CastViewModel by viewModels { CastViewModelFactory() }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun castNewInstance(movieId: Int): CastFragment {
            val fragment = CastFragment()
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
        binding = FragmentCastBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Observe the LiveData for reviews
        viewModel.cast.observe(viewLifecycleOwner) { crew ->
            if (crew != null) {
                castAdapter.submitList(crew)
            }
        }

        // Fetch reviews for the movie ID
        movieId?.let {
            viewModel.loadCast(it)
        }
    }

    private fun setupRecyclerView() {
        castAdapter = CastAdapter()
        binding.castRecyclerId.apply {
            layoutManager = GridLayoutManager(context, 2) // Use GridLayoutManager with 2 columns
            adapter = castAdapter
        }
    }
}
