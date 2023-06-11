package org.idnp.jetpackpagingsample.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.Country

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameText = view.findViewById<TextView>(R.id.textViewName) as TextView
    private val continentText = view.findViewById<TextView>(R.id.textViewContinent) as TextView
    private val capitalText = view.findViewById<TextView>(R.id.textViewCapital) as TextView

    fun bind(country: Country) {
        with(country) {
            nameText.text = name_es.toString()
            continentText.text = continent_es.toString()
            capitalText.text = capital_es.toString()
        }
    }
}