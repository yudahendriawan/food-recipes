package com.yudahendriawan.foodrecipes.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudahendriawan.foodrecipes.adapters.FavoriteRecipesAdapter
import com.yudahendriawan.foodrecipes.data.database.entities.FavoritesEntity

object FavoriteRecipesBinding {

    @BindingAdapter("setVisibility", "setData", requireAll = false)
    @JvmStatic
    fun setVisibility(
        view: View,
        favoritesEntity: List<FavoritesEntity>?,
        mAdapter: FavoriteRecipesAdapter?
    ) {
        when (view) {
            is RecyclerView -> {
                val dataCheck = favoritesEntity.isNullOrEmpty()
                view.isInvisible = dataCheck
                if (!dataCheck) {
                    favoritesEntity?.let { mAdapter?.setData(it) }
                }
            }
            else -> view.isVisible = favoritesEntity.isNullOrEmpty()
        }
    }

}
