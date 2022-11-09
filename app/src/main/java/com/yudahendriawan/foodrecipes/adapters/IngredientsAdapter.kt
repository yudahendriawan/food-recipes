package com.yudahendriawan.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.databinding.IngredientsRowLayoutBinding
import com.yudahendriawan.foodrecipes.models.ExtendedIngredient
import com.yudahendriawan.foodrecipes.util.Constants.Companion.BASE_IMAGE_URL
import com.yudahendriawan.foodrecipes.util.RecipesDiffUtil
import java.util.*

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    class MyViewHolder(private val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(extendedIngredient: ExtendedIngredient) {
            with(binding) {
                ingredientsImageView.load(BASE_IMAGE_URL + extendedIngredient.image) {
                    crossfade(600)
                    error(R.drawable.ic_error_placeholder)
                }
                ingredientsName.text = extendedIngredient.name?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                ingredientsAmount.text = extendedIngredient.amount.toString()
                ingredientsUnit.text = extendedIngredient.unit
                ingredientsConsistency.text = extendedIngredient.consistency
                ingredientsConsistency.text = extendedIngredient.original
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentIngredients = ingredientsList[position]
        holder.bind(currentIngredients)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newIngredients)
        val diffUtil = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffUtil.dispatchUpdatesTo(this)
    }

}