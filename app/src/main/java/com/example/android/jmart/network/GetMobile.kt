package com.example.android.jmart.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.jmart.data.MobileSub
import com.example.android.jmart.data.sub
import network.JbotService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMobile(context: Context) {

    private val sessionManager: SessionManager = SessionManager(context)
    var _context = context

    fun getMobile(): MutableLiveData<sub> {
        Log.i("print", "_token = ${sessionManager.fetchAuthToken()}")

        var data: MutableLiveData<sub> = MutableLiveData()

        JbotService().retrofitService.getMobileSub("Bearer ${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<MobileSub> {
            override fun onResponse(call: Call<MobileSub>, response: Response<MobileSub>) {
                if (response.isSuccessful) {
                    data.setValue(response.body()?.mobileSub05)
                    Log.i("print", "api : ${data.value}")

                } else {
                    Toast.makeText(_context, "Please login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MobileSub>, t: Throwable) {
                Log.i("print", t.message.toString())
            }
        })
        return data
    }
}