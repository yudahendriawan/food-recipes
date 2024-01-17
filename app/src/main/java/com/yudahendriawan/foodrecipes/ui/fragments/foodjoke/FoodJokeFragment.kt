package com.yudahendriawan.foodrecipes.ui.fragments.foodjoke

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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

    private var foodJoke = "No food joke."

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.food_joke_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.share_food_joke_menu) {
                    val shareIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_TEXT, foodJoke)
                        this.type = "text/plain"
                    }
                    startActivity(shareIntent)
                } else if (menuItem.itemId == R.id.refresh_food_jokes) {
                    mainViewModel.getFoodJoke(API_KEY)
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        mainViewModel.getFoodJoke(API_KEY)
        mainViewModel.foodJokeResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.foodJokeTextView.text = response.data?.text
                    if (response.data != null) {
                        foodJoke = response.data.text
                    }
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
                if (!db.isNullOrEmpty()) {
                    binding.foodJokeTextView.text = db[0].foodJoke.text
                    foodJoke = db[0].foodJoke.text
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}