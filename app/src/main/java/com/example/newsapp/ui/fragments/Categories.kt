package com.example.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.databinding.FragmentCountriesBinding
import com.example.newsapp.databinding.FragmentDisplayNewsBinding
import com.example.newsapp.utils.layoutAnimation
import com.example.newsapp.utils.toast
import com.example.newsapp.viewmodels.CategoriesViewModel
import com.example.newsapp.viewmodels.NewsViewModel

class Categories : Fragment(R.layout.fragment_categories) {

    private lateinit var viewModel: CategoriesViewModel
    lateinit var binding: FragmentCategoriesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        binding.apply {
            fragmentCategoryViewModel = viewModel
            lifecycleOwner = this@Categories
        }

        makeApiCallCoroutinesUsa()
    }

    private fun makeApiCallCoroutinesUsa(): CategoriesViewModel {
        viewModel.getAllCategories().observe(viewLifecycleOwner) {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                binding.newsRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation =
                        layoutAnimation(requireContext(), R.anim.layout_animation_fall_down)
                }
                binding.loadingAnimation.visibility = View.GONE
                viewModel.setAdapterData(it.sources)

            } else {
                binding.apply {
                    noInternet.visibility = View.VISIBLE
                    loadingAnimation.visibility = View.GONE
                }
                requireContext().toast("Check Your Internet Connection")
            }
        }

        return viewModel
    }
}