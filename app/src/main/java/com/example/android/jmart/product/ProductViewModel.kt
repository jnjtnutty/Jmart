package com.example.android.jmart.product

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.network.Api
import com.example.android.jmart.network.JbotService
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductViewModel(private val database: ProductDataDAO, application: Application) : AndroidViewModel(
    application
){
    class model(
        private var modelName: String,
        private var image: String,
        private var memory: List<String>,
        private var price: List<Double>
    )
    private var _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token

    private var _mobileSub = MutableLiveData<String>()
    val mobileSub: LiveData<String>
        get() = _mobileSub

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var data = MutableLiveData<ProductData?>()
    private val allProduct = database.getAllProduct()

    val allProductString = Transformations.map(allProduct) { allProduct ->
        var productString = ""
        for (product in allProduct) {
            productString += product.productName + "\n"
        }
        return@map productString
    }

    init {
        initializeProduct()
        onLogin()
        Log.i("Product", "Product ViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("Product", "Product ViewModel destroyed")
    }
    private fun initializeProduct() {
        uiScope.launch {
            data.value = getProductFromDatabase()
        }
    }
    private suspend fun getProductFromDatabase():  ProductData? {
        Log.i("Product", "getProductFromDatabase")
        return withContext(Dispatchers.IO) {
            val product = database.getProduct()
//            if (product?.productName != product?.memory) {
//                product = null
//            }
            product
        }
    }
    fun onStartProduct(){
        Log.i("Product", "onStartProduct new data")
        uiScope.launch {
            val newProduct = ProductData(1, "iphone12", 64, 21000)
            insert(newProduct)
            data.value = getProductFromDatabase()
        }
    }

    fun onAddProduct(){
        Log.i("Product", "onAddProduct new data")
        uiScope.launch {
            val newProduct = ProductData(2, "iphoneXR", 128, 32000)
            insert(newProduct)
            data.value = getProductFromDatabase()
        }
    }
    private suspend fun insert(product: ProductData){
        Log.i("Product", "insert data")
        withContext(Dispatchers.IO){
            database.insert(product)
        }
    }
    fun OnStopProduct(){
        Log.i("Product", "Onstop data")
        uiScope.launch {
            val oldProduct = data.value?: return@launch
            update(oldProduct)
        }
    }
    private suspend fun update(product: ProductData){
        Log.i("Product", "Update data")
        withContext(Dispatchers.IO){
            database.update(product)
        }
    }
    fun onClear(){
        Log.i("Product", "onclear data")
        uiScope.launch {
            clear()
            data.value = null
        }
    }
    private suspend fun clear(){
        Log.i("Product", "clear data")
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    fun onLogin(){
        Log.i("Product", "on login ka")
        val userInfo = Api.LoginInfo("admin@jaymart", "Jaymart@2020")
        val service = JbotService.instance
        service.getToken(userInfo).enqueue(object : Callback<Api.LoginResponse> {
            override fun onResponse(
                call: Call<Api.LoginResponse>,
                response: Response<Api.LoginResponse>
            ) {
                _token.value = response.body()?.token
                Log.i("Product", "token get: ${token.value}")
                getIphone(service)
            }

            override fun onFailure(call: Call<Api.LoginResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getIphone(service: Api){
        service.getMobileSub("Bearer " + _token.value).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.i("Product", "token: ${token.value}")
                _mobileSub.value = response.body()?.get("mobileSub05").toString()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }



}