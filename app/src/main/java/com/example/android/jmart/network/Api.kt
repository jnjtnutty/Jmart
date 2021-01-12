package com.example.android.jmart.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface Api {

    data class LoginInfo(
        var email: String,
        var password: String
    )
    data class LoginResponse(
        val displayName: String,
        val token: String
    )
    data class MobileSubResponse(
        val display: String,
        val imageNormal: String
    )

    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun getToken(@Body userData: LoginInfo): Call<LoginResponse>

    @GET("mobileSub")
    fun getMobileSub(@Header("Authorization") authHeader: String?): Call<JsonObject>

}