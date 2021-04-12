package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCountriesBinding


class Countries : Fragment(R.layout.fragment_countries) {
    lateinit var binding: FragmentCountriesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        setUpUi()
    }

    private fun setUpUi() {
        binding.apply {
            setClickListeners(usaLayout, 0)
            setClickListeners(argentinaLayout, 1)
            setClickListeners(indiaLayout, 2)
            setClickListeners(franceLayout, 3)
            setClickListeners(serbiaLayout, 4)
            setClickListeners(brazilLayout, 5)
            setClickListeners(colombiaLayout, 6)
        }
    }


    private fun setClickListeners(view: View, position: Int){
        view.setOnClickListener {
            val passData = CountriesDirections.actionCountriesToDisplayNews(
                position
            )

            findNavController().navigate(passData)
        }
    }
}