package com.yudahendriawan.foodrecipes.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.adapters.FavoriteRecipesAdapter
import com.yudahendriawan.foodrecipes.databinding.FragmentFavoriteRecipesBinding
import com.yudahendriawan.foodrecipes.databinding.FragmentIngredientBinding
import com.yudahendriawan.foodrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding as FragmentFavoriteRecipesBinding
    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)

        setupRecyclerView(binding.favoriteRecipesRecyclerView)
        mainViewModel.readFavoriteRecipes.observe(viewLifecycleOwner) {
            mAdapter.setData(it)
        }

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}