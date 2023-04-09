package com.yudahendriawan.foodrecipes.ui.fragments.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.yudahendriawan.foodrecipes.R
import com.yudahendriawan.foodrecipes.databinding.FragmentInstructionBinding
import com.yudahendriawan.foodrecipes.databinding.FragmentOverviewBinding
import com.yudahendriawan.foodrecipes.models.Result
import com.yudahendriawan.foodrecipes.util.Constants
import com.yudahendriawan.foodrecipes.util.retrieveParcelable

class InstructionFragment : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding as FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInstructionBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.retrieveParcelable(Constants.RECIPES_RESULT_KEY)

        binding.instructionWebView.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = myBundle!!.sourceUrl
        binding.instructionWebView.loadUrl(websiteUrl)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}