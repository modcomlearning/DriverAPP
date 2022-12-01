package com.example.myapplication.services

import com.example.myapplication.interfaces.RestApi
import com.example.myapplication.models.*
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
                    println(""+response.body())
                    onResult(response.body())

                }
            }
        )
    }//End

    fun changepassword(passwordPost: PasswordPost, onResult: (TaskResp?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.change_password(passwordPost).enqueue(
            object : Callback<TaskResp> {
                override fun onFailure(call: Call<TaskResp>, t: Throwable) {
                    println("Eroooooooooooooooo"+t.message)
                    onResult(null)
                }
                override fun onResponse( call: Call<TaskResp>, response: Response<TaskResp>) {
                    //val code = response.code()
                    println(""+response.body())
                    onResult(response.body())

                }
            }
        )
    }//End





    //We will use this in our recycler View
    fun getAssignments(driverPost: DriverPost, onResult: (DriverResp?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.myassignments(driverPost).enqueue(
            object : Callback<DriverResp> {
                override fun onFailure(call: Call<DriverResp>, t: Throwable) {
                    println("Eroooooooooooooooo"+t.message)
                    onResult(null)
                }
                override fun onResponse( call: Call<DriverResp>, response: Response<DriverResp>) {
                    //val code = response.code()
                    println("ttttttttttttttt"+response.body())
                    onResult(response.body())

                }
            }
        )
    }


    //We will use this in our recycler View
    fun Trip_Ongoing(taskPost: TaskPost, onResult: (TaskResp?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.TripOngoing(taskPost).enqueue(
            object : Callback<TaskResp> {
                override fun onFailure(call: Call<TaskResp>, t: Throwable) {
                    println("Eroooooooooooooooo"+t.message)
                    onResult(null)
                }
                override fun onResponse( call: Call<TaskResp>, response: Response<TaskResp>) {
                    //val code = response.code()
                    println("ttttttttttttttt"+response.body())
                    onResult(response.body())

                }
            }
        )
    }


    fun Trip_Completed(taskPost: TaskPost, onResult: (TaskResp?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.TripCompleted(taskPost).enqueue(
            object : Callback<TaskResp> {
                override fun onFailure(call: Call<TaskResp>, t: Throwable) {
                    println("Eroooooooooooooooo"+t.message)
                    onResult(null)
                }
                override fun onResponse( call: Call<TaskResp>, response: Response<TaskResp>) {
                    //val code = response.code()
                    println("ttttttttttttttt"+response.body())
                    onResult(response.body())

                }
            }
        )
    }

}