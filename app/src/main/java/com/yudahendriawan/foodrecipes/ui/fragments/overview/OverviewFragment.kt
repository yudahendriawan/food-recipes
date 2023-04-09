package com.yudahendriawan.foodrecipes.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.databinding.FragmentOverviewBinding
import com.yudahendriawan.foodrecipes.models.Result
import com.yudahendriawan.foodrecipes.util.Constants.Companion.RECIPES_RESULT_KEY
import com.yudahendriawan.foodrecipes.util.retrieveParcelable
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding as FragmentOverviewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.retrieveParcelable(RECIPES_RESULT_KEY)

        setupLayout(myBundle)

        return binding.root
    }

    private fun setupLayout(myBundle: Result?) {
        binding.apply {
            myBundle?.apply {
                mainImageView.load(image)
                titleTextView.text = title
                likesTextView.text = aggregateLikes.toString()
                timeTextView.text = readyInMinutes.toString()
                summaryTextView.text = Jsoup.parse(summary).text()

                if (vegetarian) {
                    vegeratianImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    vegetarianTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (vegan) {
                    veganImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    veganTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (glutenFree) {
                    glutenFreeImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    glutenFreeTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (dairyFree) {
                    dairyFreeImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    dairyFreeTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (veryHealthy) {
                    healthyImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    healthyTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

                if (cheap) {
                    cheapImageView.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                    cheapTextView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}