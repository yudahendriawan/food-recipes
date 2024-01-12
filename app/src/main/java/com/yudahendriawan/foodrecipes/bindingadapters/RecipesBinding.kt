package com.yudahendriawan.foodrecipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.yudahendriawan.foodrecipes.data.database.entities.RecipesEntity
import com.yudahendriawan.foodrecipes.models.FoodRecipe
import com.yudahendriawan.foodrecipes.util.NetworkResult

object RecipesBinding {

    //requireAll to make this methods to be implemented all criteria
    @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
    @JvmStatic
    fun handleReadDataResponse(
        view: View,
        apiResponse: NetworkResult<FoodRecipe>?,
        database: List<RecipesEntity>?
    ) {
        when(view) {
            is ImageView -> {
                view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
            }
            is TextView -> {
                view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                view.text = apiResponse?.message.toString()
            }
        }
    }


}