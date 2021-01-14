package com.example.android.jmart.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.jmart.product.ProductViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDataManager(private val database: ProductDataDAO) {

    private val allProduct = database.getAllProduct()

    val allProductString = Transformations.map(allProduct) { allProduct ->
        var productString = ""
        for (product in allProduct) {
            productString += product.productName + "\n"
        }
        return@map productString
    }

    suspend fun clear(){
        Log.i("print", "clear data")
        withContext(Dispatchers.IO){
            database.clear()
        }
    }
    suspend fun update(product: ProductData){
        Log.i("print", "Update data")
        withContext(Dispatchers.IO){
            database.update(product)
        }
    }
    suspend fun insert(product: ProductData){
        Log.i("print", "insert data")
        withContext(Dispatchers.IO){
            database.insert(product)
        }
    }
    suspend fun getProductFromDatabase():  ProductData? {
        Log.i("print", "getProductFromDatabase")
        return withContext(Dispatchers.IO) {
            val product = database.getProduct()

            product
        }
    }
}