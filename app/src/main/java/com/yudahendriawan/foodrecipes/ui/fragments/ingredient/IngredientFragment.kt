package com.yudahendriawan.foodrecipes.ui.fragments.ingredient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.adapters.IngredientsAdapter
import com.yudahendriawan.foodrecipes.databinding.FragmentIngredientBinding
import com.yudahendriawan.foodrecipes.databinding.FragmentInstructionBinding
import com.yudahendriawan.foodrecipes.models.Result
import com.yudahendriawan.foodrecipes.util.Constants

class IngredientFragment : Fragment() {

    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding as FragmentIngredientBinding

    private val mAdapter: IngredientsAdapter by lazy {
        IngredientsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPES_RESULT_KEY)

        setupRecyclerView()
        myBundle?.extendedIngredient?.let {
            mAdapter.setData(it)
            Log.d(
                IngredientFragment::class.java.simpleName,
                "onCreateView: number of ingredients: ${it.size}"
            )
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        with(binding) {
            ingredientsRecyclerView.adapter = mAdapter
            ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}