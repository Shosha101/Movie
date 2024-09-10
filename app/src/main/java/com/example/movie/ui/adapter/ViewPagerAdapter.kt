package com.example.movie.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.ui.view.NowPlayingFragment
import com.example.movie.ui.view.PopularFragment
import com.example.movie.ui.view.TopRatedFragment
import com.example.movie.ui.view.UpcomingFragment

class ViewPagerAdapter(
    fragment: Fragment,
    private val tabCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NowPlayingFragment()
            1 -> UpcomingFragment() // Replace with the corresponding fragment for "Reviews"
            2 -> TopRatedFragment() // Replace with the corresponding fragment for "Cast"
            3 -> PopularFragment() // Replace with the corresponding fragment for "Cast"
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
