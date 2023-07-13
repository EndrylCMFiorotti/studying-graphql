package com.example.studying_graphql.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studying_graphql.databinding.ItemListCountryBinding
import com.example.studying_graphql.domain.CountryPresentation

class CountryAdapter(
    private val countries: List<CountryPresentation>,
    private val onClick: (code: String) -> Unit
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = countries.size

    inner class ViewHolder(private val binding: ItemListCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onClick(countryData().code) }
        }

        fun bind() {
            with(binding) {
                tvImageCountry.text = countryData().emoji
                tvNameCountry.text = countryData().name
                tvCapitalCountry.text = countryData().capital
            }
        }

        private fun countryData() = countries[adapterPosition]
    }
}