package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDisplayNewsBinding
import com.example.newsapp.utils.layoutAnimation
import com.example.newsapp.utils.toast
import com.example.newsapp.viewmodels.NewsViewModel


class DisplayNews : Fragment(R.layout.fragment_display_news) {

    private lateinit var viewModel: NewsViewModel

    var position: Int = 0

    lateinit var binding: FragmentDisplayNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        arguments.let {
            val safeArgs = DisplayNewsArgs.fromBundle(it!!)
            position = safeArgs.position
        }
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.apply {
            fragmentNewsViewModel = viewModel
            lifecycleOwner = this@DisplayNews
        }


        when (position) {
            0 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "USA"
                makeApiCallCoroutinesUsa()
            }
            1 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Argentina"
                makeApiCallCoroutinesArgentina()
            }
            3 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "France"
                makeApiCallCoroutinesFrance()
            }
            2 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "India"
                makeApiCallCoroutinesIndia()
            }

            5 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Brazil"
                makeApiCallCoroutinesBrazil()
            }

            4 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Serbia"
                makeApiCallCoroutinesSerbia()
            }

            6 -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Colombia"
                makeApiCallCoroutinesColumbia()
            }
        }
    }

    private fun makeApiCallCoroutinesUsa(): NewsViewModel {
        viewModel.getAllNewsUsa().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesArgentina(): NewsViewModel {
        viewModel.getAllNewsArgentina().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesIndia(): NewsViewModel {
        viewModel.getAllNewsIndia().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesFrance(): NewsViewModel {
        viewModel.getAllNewsFrance().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesSerbia(): NewsViewModel {
        viewModel.getAllNewsSerbia().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesBrazil(): NewsViewModel {
        viewModel.getAllNewsBrazil().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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

    private fun makeApiCallCoroutinesColumbia(): NewsViewModel {
        viewModel.getAllNewsColombia().observe(viewLifecycleOwner) {
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
                viewModel.setAdapterData(it.articles)

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