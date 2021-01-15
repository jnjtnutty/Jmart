package com.example.android.jmart.promotion

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.android.jmart.R
import com.example.android.jmart.data.Promotion
import com.example.android.jmart.data.sub
import com.example.android.jmart.database.ProductData
import com.example.android.jmart.database.ProductDataDAO
import com.example.android.jmart.database.PromotionPicData
import com.example.android.jmart.network.GetPromotion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PromotionViewModel (database: ProductDataDAO, application: Application) : AndroidViewModel(
    application
) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var _database = database

    fun getPromotion(): LiveData<Promotion> {
        return GetPromotion(getApplication()).getPro()
    }

    fun savePromotionToDatabase(pro: Promotion) {
        uiScope.launch {
            _database.insertPromotion(PromotionPicData(1, "image01", pro.image01))
            _database.insertPromotion(PromotionPicData(2, "image02", pro.image02))
            _database.insertPromotion(PromotionPicData(3, "image03", pro.image03))
            _database.insertPromotion(PromotionPicData(4, "image04", pro.image04))
            _database.insertPromotion(PromotionPicData(5, "image05", pro.image05))
            _database.insertPromotion(PromotionPicData(6, "image06", pro.image06))
            _database.insertPromotion(PromotionPicData(7, "image07", pro.image07))
        }
    }

}