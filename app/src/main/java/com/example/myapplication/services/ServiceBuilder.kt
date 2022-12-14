package com.example.myapplication.services

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    //private val client = OkHttpClient.Builder().build()

        val prefs = MainActivity.mainActivity.getSharedPreferences("storage", AppCompatActivity.MODE_PRIVATE)
        val Token = prefs.getString("Token", "")

        var okHttpClient = OkHttpClient.Builder().addInterceptor {chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $Token")
                .build()
            chain.proceed(newRequest)
        }

        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://coding.co.ke/fleet100/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun<T> buildService(service: Class<T>): T{
        println("This one222222222222 $Token")
        return retrofit.create(service)
    }
}