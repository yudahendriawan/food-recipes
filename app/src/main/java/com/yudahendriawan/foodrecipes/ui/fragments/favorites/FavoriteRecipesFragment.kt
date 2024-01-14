package com.yudahendriawan.foodrecipes.ui.fragments.favorites

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: FavoriteRecipesAdapter by lazy {
        FavoriteRecipesAdapter(
            requireActivity(),
            mainViewModel
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.favorites_recipe_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.delete_all_favorites_recipes_menu) {
                    mainViewModel.deleteAllFavoriteRecipes()
                    showSnackBar()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setupRecyclerView(binding.favoriteRecipesRecyclerView)

        return binding.root
    }

    private fun showSnackBar() {
        Snackbar.make(binding.root, "All recipes removed.", Snackbar.LENGTH_SHORT)
            .setAction("OK") {}.show()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.clearContextualActionMode()
        _binding = null
    }

}