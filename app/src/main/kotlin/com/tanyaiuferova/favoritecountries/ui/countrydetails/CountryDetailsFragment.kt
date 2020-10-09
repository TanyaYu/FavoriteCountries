package com.tanyaiuferova.favoritecountries.ui.countrydetails

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.BaseDialogFragment
import com.tanyaiuferova.favoritecountries.utils.Schedulers.main
import com.tanyaiuferova.favoritecountries.utils.hideSoftKeyboard
import com.tanyaiuferova.favoritecountries.utils.showSoftKeyboard
import com.tanyaiuferova.favoritecountries.viewmodels.CountryDetailsViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragmnet_country_details.*

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountryDetailsFragment : BaseDialogFragment(R.layout.fragmnet_country_details) {

    private val viewModel by viewModels<CountryDetailsViewModel>()

    private val args: CountryDetailsFragmentArgs by navArgs()

    private val dataBinding = CountryDetailsDataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.onViewCreated(view)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        save_btn.setOnClickListener {
            viewModel.save()
            findNavController().popBackStack(R.id.home, false)
        }

        notes_et.addTextChangedListener(
            afterTextChanged = { editable ->
                val notes = editable?.toString().orEmpty()
                viewModel.updateNotes(notes)
            })
    }

    override fun onAttached() {
        disposable += viewModel.title.observeOn(main)
            .subscribeBy(onSuccess = { dataBinding.title = it })
        disposable += viewModel.notes.observeOn(main)
            .subscribeBy(onNext = { dataBinding.notes = it })
        viewModel.updateId(args.id)
    }

    override fun onResume() {
        super.onResume()
        notes_et.showSoftKeyboard()
    }

    override fun onPause() {
        super.onPause()
        notes_et.hideSoftKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding.onDestroyView()
    }
}