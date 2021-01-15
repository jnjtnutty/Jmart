package com.example.android.jmart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotion_url")
data class PromotionPicData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "imageId")
    var id: Int = 0,

    @ColumnInfo(name = "imageName")
    val imageName: String?=null,

    @ColumnInfo(name = "url")
    val url: String?= null
)
