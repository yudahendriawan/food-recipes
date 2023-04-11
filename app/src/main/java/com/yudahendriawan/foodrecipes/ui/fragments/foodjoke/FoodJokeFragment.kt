package com.yudahendriawan.foodrecipes.ui.fragments.foodjoke

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.databinding.FragmentFavoriteRecipesBinding
import com.yudahendriawan.foodrecipes.databinding.FragmentFoodJokeBinding
import com.yudahendriawan.foodrecipes.util.Constants.Companion.API_KEY
import com.yudahendriawan.foodrecipes.util.NetworkResult
import com.yudahendriawan.foodrecipes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding as FragmentFoodJokeBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        mainViewModel.getFoodJoke(API_KEY)
        mainViewModel.foodJokeResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.foodJokeTextView.text = response.data?.text
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    Log.d("FoodJokeFragment", "Loading")
                }
            }
        }

        return binding.root
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readFoodJoke.observe(viewLifecycleOwner) { db ->
                if (db.isNotEmpty() && db != null) {
                    binding.foodJokeTextView.text = db[0].text
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}