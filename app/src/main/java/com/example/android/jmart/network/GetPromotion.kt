package com.example.android.jmart.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.android.jmart.data.Promotion
import network.JbotService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPromotion(context: Context) {

    private val sessionManager: SessionManager = SessionManager(context)
    var _context = context

    fun getPro(){
        JbotService().retrofitService.getPromotion("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<Promotion> {
            override fun onResponse(call: Call<Promotion>, response: Response<Promotion>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.i("print", "modile data : $data")
                }else{
                    Toast.makeText(_context,"Please login", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Promotion>, t: Throwable) {
                Log.i("print", t.message.toString())
            }
        })
    }
}