package com.example.movie.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.R
import com.example.movie.databinding.FragmentHomePageBinding
import com.example.movie.ui.adapter.HeadAdapter
import com.example.movie.ui.adapter.ViewPagerAdapter
import com.example.movie.ui.viewmodel.HomePageViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomePageFragment : Fragment() {

    private val homePageViewModel: HomePageViewModel by viewModels()
    private lateinit var headAdapter: HeadAdapter
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use DataBindingUtil to inflate the layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.tabBar.viewPager
        tabLayout = binding.tabBar.tabLayout

        val adapter = ViewPagerAdapter(this, 4) // Adjust the number of pages
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.now_playing)
                1 -> getString(R.string.up_coming)
                2 -> getString(R.string.top_rated)
                3 -> getString(R.string.popular)
                else -> null
            }
        }.attach()

        homePageViewModel.getTopRated()
        homePageViewModel.movies.observe(viewLifecycleOwner, Observer { headView ->

            // Initialize the adapter with a click listener
            headAdapter = HeadAdapter { selectedMovie ->
                // Handle item click, navigate or perform any action
                // Example: Navigate to a detail screen
                val action =
                    HomePageFragmentDirections.actionHomeItemIDToDetailFragId(selectedMovie.id)
                findNavController().navigate(action)
            }
            headAdapter.submitList(headView.results)
            binding.headRecyclerId.adapter = headAdapter
        })
    }
}
