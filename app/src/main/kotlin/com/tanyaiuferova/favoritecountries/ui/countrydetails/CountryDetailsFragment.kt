package com.tanyaiuferova.favoritecountries.ui.countrydetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.utils.Schedulers.main
import com.tanyaiuferova.favoritecountries.utils.hideSoftKeyboard
import com.tanyaiuferova.favoritecountries.utils.showSoftKeyboard
import com.tanyaiuferova.favoritecountries.viewmodels.CountryDetailsViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragmnet_country_details.*
import kotlin.properties.Delegates

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountryDetailsFragment : BaseFragment(R.layout.fragmnet_country_details) {

    private val viewModel by viewModels<CountryDetailsViewModel>()

    private val args: CountryDetailsFragmentArgs by navArgs()

    //TODO implement DataBinding
    private var item by Delegates.observable<CountryDetailsItem?>(null) { _, _, newValue ->
        if (view != null && newValue != null) bindItem(newValue)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.home, false)
        }
        item?.let(::bindItem)
    }

    override fun onResume() {
        super.onResume()
        notes_et.showSoftKeyboard()
    }

    override fun onPause() {
        super.onPause()
        notes_et.hideSoftKeyboard()
    }

    override fun onAttached() {
        disposable += viewModel.country.observeOn(main).subscribeBy(
            onNext = { item = it }
        )
        viewModel.onIdChanged(args.id)
    }

    private fun bindItem(item: CountryDetailsItem) {
        toolbar.title = item.title
        notes_et.setText(item.notes)
        save_btn.setOnClickListener {
            val notes = notes_et.text.toString()
            viewModel.onSaveClick(item.id, notes) //todo two way databinding
            findNavController().popBackStack(R.id.home, false)
        }
    }
}