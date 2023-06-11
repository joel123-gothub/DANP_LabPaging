package org.idnp.jetpackpagingsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.idnp.jetpackpagingsample.adapters.CountryAdapter
import org.idnp.jetpackpagingsample.model.AppDatabase
import org.idnp.jetpackpagingsample.model.UserRepositoryRoom
import org.idnp.jetpackpagingsample.paging.UserViewModel
import org.idnp.jetpackpagingsample.tools.FillDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val readfile = FillDatabase()
        val jsoncountries = readfile.filltable(this)
        Log.d("----", jsoncountries)

        val repository = UserRepositoryRoom(AppDatabase.getInstance(this))
        GlobalScope.launch(Dispatchers.Main) {
            val countries = readfile.parseCountriesList(jsoncountries)
            repository.insertCountries(countries)
        }

        val viewModel = UserViewModel(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }

}