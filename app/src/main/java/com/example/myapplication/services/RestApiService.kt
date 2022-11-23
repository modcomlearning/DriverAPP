package com.example.myapplication.services

import com.example.myapplication.interfaces.RestApi
import com.example.myapplication.models.LoginResp
import com.example.myapplication.models.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
class RestApiService {
    fun addUser(userData: UserInfo, onResult: (LoginResp?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<LoginResp> {
                override fun onFailure(call: Call<LoginResp>, t: Throwable) {
                    println("Eroooooooooooooooo"+t.message)
                    onResult(null)
                }
                override fun onResponse( call: Call<LoginResp>, response: Response<LoginResp>) {
                    //val code = response.code()
                   // println(""+response.body())
                    onResult(response.body())

                }
            }
        )
    }
}