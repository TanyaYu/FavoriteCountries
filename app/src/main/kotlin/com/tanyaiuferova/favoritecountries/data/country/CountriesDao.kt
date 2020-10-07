package com.tanyaiuferova.favoritecountries.data.country

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
@Dao
interface CountriesDao {
    @Query("SELECT * FROM country")
    fun getAll(): Flowable<List<Country>>

    @Query("SELECT * FROM country where id like :pattern OR name like :pattern")
    fun search(pattern: String): Flowable<List<Country>>

    @Query("SELECT * FROM country WHERE id = :id")
    fun getById(id: String): Single<Country>

    @Insert(onConflict = IGNORE)
    fun insertAll(virtues: List<Country>): Completable

    @Query("SELECT * FROM country WHERE isFavorite = :isFavorite")
    fun getFavorites(isFavorite: Boolean): Flowable<List<Country>>

    @Query("UPDATE country SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavorite(id: String, isFavorite: Boolean): Completable

    @Query("DELETE FROM country WHERE isFavorite = 0")
    fun deleteAllUnfavorites(): Completable

}