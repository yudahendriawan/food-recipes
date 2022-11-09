package com.yudahendriawan.foodrecipes.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.models.Result
import com.yudahendriawan.foodrecipes.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

object RecipesRowBinding {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("setNumberOfLikes")
    @JvmStatic
    fun setNumberOfLikes(textView: TextView, likes: Int) {
        textView.text = likes.toString()
    }

    @BindingAdapter("setNumberOfMinutes")
    @JvmStatic
    fun setNumberOfMinutes(textView: TextView, minutes: Int) {
        textView.text = minutes.toString()
    }

    @BindingAdapter("applyVeganColor")
    @JvmStatic
    fun applyVeganColor(view: View, vegan: Boolean) {
        if (vegan) {
            when (view) {
                is TextView -> {
                    view.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }

                is ImageView -> {
                    view.setColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
            }
        }
    }

    @BindingAdapter("onRecipeClickListener")
    @JvmStatic
    fun onRecipesClickListener(recipesRowLayout: ConstraintLayout, result: Result) {
        recipesRowLayout.setOnClickListener {
            try {
                val action = RecipesFragmentDirections.actionRecipesFragmentToDetailActivity(result)
                recipesRowLayout.findNavController().navigate(action)
            } catch (e: Exception) {
                Log.d("onRecipesClickListener", e.toString())
            }
        }
    }

    @BindingAdapter("parseHtml")
    @JvmStatic
    fun parseHtml(text: TextView, desc: String?) {
        if (desc != null) {
            text.text = Jsoup.parse(desc).text()
        }
    }

}
