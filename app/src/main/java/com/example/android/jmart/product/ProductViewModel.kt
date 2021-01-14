package com.example.android.jmart.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.jmart.data.sub
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDataManager
import com.example.android.jmart.network.GetMobile
import kotlinx.coroutines.*
import java.util.*

class ProductViewModel(private val database: ProductDataDAO, application: Application) : AndroidViewModel(
        application) {

    private var data = MutableLiveData<ProductData?>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val dataManager = ProductDataManager(database)

    init {
        initializeProduct()
        Log.i("print", "Product ViewModel created")
    }

    fun getIphone() : LiveData<sub> {
        return GetMobile(getApplication()).getMobile()
    }

    fun saveDataToProductDatabase(mobiles: List<sub>){
        uiScope.launch {
            for( mb in mobiles)
            {

            }
            data.value = dataManager.getProductFromDatabase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("print", "Product ViewModel destroyed")
    }

    private fun initializeProduct() {
        uiScope.launch {
            data.value = dataManager.getProductFromDatabase()
        }
    }


    fun onStartProduct(){
        Log.i("print", "onStartProduct new data")
        uiScope.launch {
            val newProduct = ProductData(1, "iphone12", 64, 21000)
            dataManager.insert(newProduct)
            data.value = dataManager.getProductFromDatabase()
        }
    }

    fun OnStopProduct(){
        Log.i("print", "Onstop data")
        uiScope.launch {
            val oldProduct = data.value?: return@launch
            dataManager.update(oldProduct)
        }
    }

    fun onClear(){
        Log.i("print", "onclear data")
        uiScope.launch {
            dataManager.clear()
            data.value = null
        }
    }

}