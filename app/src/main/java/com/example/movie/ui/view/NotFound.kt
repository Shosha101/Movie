package com.example.movie.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movie.R
import com.example.movie.databinding.FragmentNotFoundBinding

class NotFoundFragment : Fragment() {

    private lateinit var databinding: FragmentNotFoundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the databinding object using the inflate method
        databinding = FragmentNotFoundBinding.inflate(inflater, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example: Navigating to the home page on a button click
        databinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentNotFoundFragment_to_homeItemID)
        }
    }
}
