package com.example.android.jmart.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.android.jmart.data.Promotion
import com.example.android.jmart.data.sub
import network.JbotService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPromotion(context: Context) {

    private val sessionManager: SessionManager = SessionManager(context)
    var _context = context

    fun getPro(): MutableLiveData<Promotion> {

        val data: MutableLiveData<Promotion> = MutableLiveData()

        JbotService().retrofitService.getPromotion("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<Promotion> {

            override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                if (response.isSuccessful) {
                    data.setValue(response.body())
                    Log.i("print", "promotinon data : $data")
                }else{
                    Toast.makeText(_context,"Please login", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Promotion>, t: Throwable) {
                Log.i("print", t.message.toString())
            }
        })
        return data
    }
}