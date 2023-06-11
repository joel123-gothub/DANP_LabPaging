package org.idnp.jetpackpagingsample.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM country where countryId >= :startRow and countryId < :endRow")
    fun getCountries(startRow:Int, endRow:Int):List<Country>

    @Insert
    suspend fun insert(countries: List<Country>)

}