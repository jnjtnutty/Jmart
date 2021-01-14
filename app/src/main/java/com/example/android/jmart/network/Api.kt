package network

import com.example.android.jmart.data.LoginResponse
import com.example.android.jmart.data.MobileSub
import com.example.android.jmart.data.Promotion
import retrofit2.Call
import retrofit2.http.*

interface Api {

    data class LoginInfo(
            var email: String,
            var password: String
    )

    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun getToken(@Body userData: LoginInfo): Call<LoginResponse>

    @GET("mobileSub")
    fun getMobileSub(@Header("Authorization") authHeader: String?): Call<MobileSub>

    @GET("promotion")
    fun getPromotion(@Header("Authorization") authHeader: String?): Call<Promotion>

}