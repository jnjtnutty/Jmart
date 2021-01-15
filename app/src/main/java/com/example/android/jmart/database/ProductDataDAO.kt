package com.example.android.jmart.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductData)

    @Update
    fun update(product: ProductData)

    @Query("SELECT * from product_table WHERE productId = :key")
    fun get(key: Int): ProductData?

    @Query("DELETE FROM product_table")
    fun clear()

    @Query("SELECT * FROM product_table ORDER BY productId DESC")
    fun getAllProduct(): LiveData<List<ProductData>>

    @Query("SELECT * FROM product_table ORDER BY productId DESC LIMIT 1")
    fun getProduct(): ProductData

}