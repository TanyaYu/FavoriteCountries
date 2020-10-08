package com.tanyaiuferova.favoritecountries.data.country

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
@Dao
interface CountriesDao {
    @Query("SELECT * FROM country")
    fun getAll(): Flowable<List<Country>>

    @Query(
        """
        SELECT * FROM country 
        WHERE isFavorite = 0
        AND (
            id LIKE '%' || :query || '%' 
            OR name LIKE '%' || :query || '%'
        )
        """
    )
    fun searchNonFavorites(query: String?): Maybe<List<Country>>

    @Query("SELECT * FROM country WHERE id = :id")
    fun getById(id: String): Maybe<Country>

    @Insert(onConflict = IGNORE)
    fun insertAll(virtues: List<Country>): Completable

    @Query("SELECT * FROM country WHERE isFavorite = :isFavorite")
    fun getFavorites(isFavorite: Boolean): Flowable<List<Country>>

    @Query("UPDATE country SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: String, isFavorite: Boolean): Completable

    @Query("UPDATE country SET notes = :notes WHERE id = :id")
    fun updateNotes(id: String, notes: String?): Completable

    @Query("DELETE FROM country WHERE isFavorite = 0")
    fun deleteAllNonFavorites(): Completable

}