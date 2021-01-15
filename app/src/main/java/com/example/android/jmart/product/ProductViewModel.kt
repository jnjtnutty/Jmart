package com.example.android.jmart.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.jmart.data.sub
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.ProductDataManager
import com.example.android.jmart.network.GetMobile
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import java.util.*

class ProductViewModel(database: ProductDataDAO, application: Application) : AndroidViewModel(
    application
) {

    private var data = MutableLiveData<ProductData?>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val dataManager = ProductDataManager(database)

    init {
        initializeProduct()
    }

    private fun initializeProduct() {
        uiScope.launch {
            data.value = dataManager.getProductFromDatabase()
        }
    }

    fun getIphone() : LiveData<sub> {
        return GetMobile(getApplication()).getMobile()
    }

    fun saveDataToProductDatabase(mobiles: sub) {
        uiScope.launch {
            val jsonMobiles: JsonObject = Gson().toJsonTree(mobiles).asJsonObject;
            var product: ProductData
            var idIndex = 0
            for ( (key, value) in jsonMobiles.entrySet() ) {
                if( key!="brand" ) {
                    val valueObject: JsonObject = jsonMobiles.get(key).asJsonObject
                    if( valueObject.get("model").toString().replace("\"", "") != "disable" )
                    {
                        product = ProductData(productId = idIndex,model = valueObject.get("model").toString().replace("\"", ""),
                            display = valueObject.get("display").toString().replace("\"", ""),
                            imageNormal = valueObject.get("imageNormal").toString().replace("\"", ""))
                        dataManager.insert(product)
                        data.value = dataManager.getProductFromDatabase()
                        Log.i("print","data -> ${data.value}")
                        idIndex +=1
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("print", "Product ViewModel destroyed")
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