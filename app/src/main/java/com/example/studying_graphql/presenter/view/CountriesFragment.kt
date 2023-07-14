package com.example.studying_graphql.presenter.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.studying_graphql.databinding.DialogCountryBinding
import com.example.studying_graphql.databinding.FragmentCountriesBinding
import com.example.studying_graphql.domain.CountryDetailsPresentation
import com.example.studying_graphql.domain.CountryPresentation
import com.example.studying_graphql.presenter.adapter.CountryAdapter
import com.example.studying_graphql.presenter.viewModel.CountryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {
    private lateinit var binding: FragmentCountriesBinding
    private lateinit var dialogBinding: DialogCountryBinding
    private lateinit var dialog: Dialog
    private val viewModel by viewModel<CountryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCountries()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            countries.observe(viewLifecycleOwner) {
                displayCountriesOnScreen(it)
            }
            country.observe(viewLifecycleOwner) {
                displayCountryDialog(it)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }
            empty.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "List is empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayCountriesOnScreen(list: List<CountryPresentation>) {
        binding.rvCountries.adapter = CountryAdapter(list) {
            viewModel.getCountry(it)
            setupCountryDialog()
        }
    }

    private fun setupCountryDialog() {
        dialogBinding = DialogCountryBinding.inflate(LayoutInflater.from(requireContext()))
        dialog = Dialog(requireContext()).apply {
            setContentView(dialogBinding.root)
            setCancelable(true)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
        setupLoading(true)
    }

    @SuppressLint("SetTextI18n")
    private fun displayCountryDialog(it: CountryDetailsPresentation) {
        with(dialogBinding) {
            tvImageCountry.text = it.emoji
            tvNameCountry.text = it.name
            tvCapitalCountry.text = "Capital: ${it.capital}"
            tvContinentCountry.text = "Continent: ${it.continent}"
            tvCurrencyCountry.text = "Currency: ${it.currency}"
            tvLanguageCountry.text = "Language: ${it.languages}"
        }
        setupLoading(false)
    }

    private fun setupLoading(loading: Boolean) {
        dialogBinding.pbLoading.isVisible = loading
    }
}