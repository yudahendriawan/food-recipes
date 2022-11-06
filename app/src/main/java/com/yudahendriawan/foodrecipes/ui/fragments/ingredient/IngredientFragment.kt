package com.yudahendriawan.foodrecipes.ui.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.databinding.FragmentIngredientBinding
import com.yudahendriawan.foodrecipes.databinding.FragmentInstructionBinding

class IngredientFragment : Fragment() {

    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding as FragmentIngredientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)
        return binding.root
    }

}