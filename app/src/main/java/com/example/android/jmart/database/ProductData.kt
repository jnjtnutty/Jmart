package com.example.android.jmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductData(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "productId")
        var productId: Int = 0,

        @ColumnInfo(name = "productName")
        val model: String?=null,

        @ColumnInfo(name = "memory")
        val display: String?= null,

        @ColumnInfo(name = "price")
        val imageNormal: String?= null
)
