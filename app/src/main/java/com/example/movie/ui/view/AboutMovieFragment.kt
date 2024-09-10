package com.example.movie.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie.R
import com.example.movie.databinding.FragmentAboutMovieBinding

class AboutMovieFragment : Fragment() {

    private lateinit var binding: FragmentAboutMovieBinding
    private var movieOverview: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the overview text in the TextView
        movieOverview?.let {
            binding.overViewId.text = it // Assuming textViewOverview is the ID of your TextView
        }
    }

    companion object {
        private const val ARG_OVERVIEW = "movie_overview"

        fun newInstance(overview: String): AboutMovieFragment {
            val fragment = AboutMovieFragment()
            val args = Bundle().apply {
                putString(ARG_OVERVIEW, overview)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieOverview = it.getString(ARG_OVERVIEW)
        }
    }
}
