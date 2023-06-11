package org.idnp.jetpackpagingsample.model

import org.idnp.jetpackpagingsample.entities.Country

class UserRepositoryRoom(private val appDatabase: AppDatabase){

    suspend fun getCountries(startRow:Int, numRows:Int): List<Country> {
        val endRow = startRow + numRows
        return appDatabase.CountryDao().getCountries(startRow,endRow)
    }

    suspend fun insertCountries(countries: List<Country>) {
        appDatabase.CountryDao().insert(countries)
    }

}
