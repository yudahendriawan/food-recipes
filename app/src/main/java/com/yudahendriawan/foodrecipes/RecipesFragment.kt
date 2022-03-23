package com.yudahendriawan.foodrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yudahendriawan.foodrecipes.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private var _binding : FragmentRecipesBinding? = null
    private val binding get() = _binding as FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)

        binding.recyclerView.showShimmer()

        return binding.root
    }

}