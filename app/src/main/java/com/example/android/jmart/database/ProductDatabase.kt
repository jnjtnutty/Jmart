package com.example.android.jmart.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(ProductData::class)], version = 1,  exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDatabaseDao: ProductDataDAO
    companion object {
        private var INSTANCE: ProductDatabase? = null
        fun getInstance(context: Context): ProductDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                    ProductDatabase::class.java,
                    "product_database")
                    .allowMainThreadQueries().build() }
            return INSTANCE as ProductDatabase
        }
    }
}
