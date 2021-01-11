package com.example.android.jmart.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDatabase
import kotlinx.coroutines.*
import java.util.*

class ProductViewModel(private val database: ProductDataDAO, application: Application) : AndroidViewModel(application){
    class model(
        private var modelName: String,
        private var image: String,
        private var memory: List<String>,
        private var price: List<Double>
    )

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    private var data = MutableLiveData<ProductData?>()
    private val allProduct = database.getAllProduct()
    val allProductString = Transformations.map(allProduct) {allProduct ->
        var productString = ""
        for (product in allProduct) {
            productString += product.productName + "\n"
        }
        return@map productString
    }

    init {
        initializeProduct()
        Log.i("Product","Product ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("Product","Product ViewModel destroyed")
    }
    private fun initializeProduct() {
        uiScope.launch {
            data.value = getProductFromDatabase()
        }
    }
    private suspend fun getProductFromDatabase():  ProductData? {
        Log.i("Product","getProductFromDatabase")
        return withContext(Dispatchers.IO) {
            val product = database.getProduct()
//            if (product?.productName != product?.memory) {
//                product = null
//            }
            product
        }
    }
    fun onStartProduct(){
        Log.i("Product","onStartProduct new data")
        uiScope.launch {
            val newProduct = ProductData(1,"iphone12",64,21000)
            insert(newProduct)
            data.value = getProductFromDatabase()
        }
    }

    fun onAddProduct(){
        Log.i("Product","onAddProduct new data")
        uiScope.launch {
            val newProduct = ProductData(2,"iphoneXR",128,32000)
            insert(newProduct)
            data.value = getProductFromDatabase()
        }
    }
    private suspend fun insert(product: ProductData){
        Log.i("Product","insert data")
        withContext(Dispatchers.IO){
            database.insert(product)
        }
    }
    fun OnStopProduct(){
        Log.i("Product","Onstop data")
        uiScope.launch {
            val oldProduct = data.value?: return@launch
            update(oldProduct)
        }
    }
    private suspend fun update(product: ProductData){
        Log.i("Product","Update data")
        withContext(Dispatchers.IO){
            database.update(product)
        }
    }
    fun onClear(){
        Log.i("Product","onclear data")
        uiScope.launch {
            clear()
            data.value = null
        }
    }
    private suspend fun clear(){
        Log.i("Product","clear data")
        withContext(Dispatchers.IO){
            database.clear()
        }
    }
}