package com.example.android.jmart

import android.util.Log
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {
    init {
        Log.i("ProductViewModel","ProductViewModel created")
    }
    override fun onCleared(){
        super.onCleared()
        Log.i("ProductViewModel","Product destroyed")
    }
}