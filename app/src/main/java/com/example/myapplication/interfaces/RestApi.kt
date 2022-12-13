package com.example.myapplication.interfaces

import com.example.myapplication.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface RestApi {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun addUser(@Body userData: UserInfo): Call<LoginResp>

    @Headers("Content-Type: application/json")
    @POST("change_password")
    fun change_password(@Body passwordPost: PasswordPost): Call<TaskResp>


    @Headers("Content-Type: application/json")
    @POST("myassignments")
    fun myassignments(@Body driverPost: DriverPost): Call<DriverResp>

    @Headers("Content-Type: application/json")
    @POST("myservices")
    fun myservices(@Body driverPost: DriverPost): Call<DriverResp>


    @Headers("Content-Type: application/json")
    @PUT("TripOngoing")
    fun TripOngoing(@Body taskPost: TaskPost): Call<TaskResp>

    @Headers("Content-Type: application/json")
    @PUT("TripCompleted")
    fun TripCompleted(@Body taskPost: TaskPost): Call<TaskResp>



}