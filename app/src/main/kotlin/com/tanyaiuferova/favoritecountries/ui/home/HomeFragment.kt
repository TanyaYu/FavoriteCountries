package com.tanyaiuferova.favoritecountries.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
    }

    override fun onAttached() {
        viewModel.toString()
    }
}