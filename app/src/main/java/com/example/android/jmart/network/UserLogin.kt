package com.example.android.jmart.network

import android.content.Context
import android.util.Log
import com.example.android.jmart.data.LoginResponse
import network.Api
import network.JbotService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLogin(context: Context) {

    private val sessionManager: SessionManager = SessionManager(context)
    private val contextLocal = context
    fun userLogin(){
        val userInfo = Api.LoginInfo("admin@jaymart", "Jaymart@2020")
        JbotService().retrofitService.getToken(userInfo).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()
                sessionManager.saveAuthToken(loginResponse!!.token)
                Log.i("print", "token : $loginResponse")
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("print", t.message.toString())
            }
        })
    }
}