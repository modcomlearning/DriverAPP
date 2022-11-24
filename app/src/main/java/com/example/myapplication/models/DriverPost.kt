package com.example.myapplication.models


import com.google.gson.annotations.SerializedName
data class DriverPost (
    @SerializedName("driver_id") val driverId: String?
)

//This defines what the API is expecting in the Body