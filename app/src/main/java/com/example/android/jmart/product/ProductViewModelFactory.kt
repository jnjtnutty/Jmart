package com.example.android.jmart.product
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.jmart.database.ProductDataDAO
import java.lang.IllegalArgumentException

class ProductViewModelFactory (
    private val dataSource: ProductDataDAO,
    private val application:Application): ViewModelProvider.Factory{

    @Suppress("unchecked_case")
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}