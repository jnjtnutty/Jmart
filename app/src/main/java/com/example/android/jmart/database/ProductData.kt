package com.example.android.jmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductData(
        @PrimaryKey(autoGenerate = true)
        var productId: Long = -1,

        @ColumnInfo(name = "productName")
        val productName: String?=null,

        @ColumnInfo(name = "memory")
        val memory: Int?= null,

        @ColumnInfo(name = "price")
        val price: Int?= null,
)
