package com.example.android.jmart

import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger

class ProductViewModel : ViewModel() {
    init {
        Logger.d("ProductViewModel created")
    }
    override fun onCleared(){
        super.onCleared()
        Logger.d("Product destroyed")
    }
}