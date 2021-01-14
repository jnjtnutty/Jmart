package com.example.android.jmart.network

import android.content.Context
import android.util.Log
import com.example.android.jmart.data.MobileSub
import network.JbotService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMobile(context: Context) {

    private val sessionManager: SessionManager = SessionManager(context)

    fun getMobile(){

        Log.i("print", "_token = ${sessionManager.fetchAuthToken()}")

        JbotService().retrofitService.getMobileSub("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<MobileSub> {
            override fun onResponse(call: Call<MobileSub>, response: Response<MobileSub>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.i("print", "modile data : $data")
                }
            }

            override fun onFailure(call: Call<MobileSub>, t: Throwable) {
                Log.i("print", t.message.toString())
            }
        })
    }
}