package com.skfo763.storage.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getLikedProductList(): Single<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLikedProduct(vararg productEntity: ProductEntity): Completable

    @Delete
    fun deleteLikedProduct(vararg productEntity: ProductEntity): Completable

    @Query("DELETE FROM product WHERE id == (:idx)")
    fun deleteLikedProduct(vararg idx: Int): Completable

    @Query("DELETE FROM product")
    fun deleteAllLikedProduct(): Completable

}