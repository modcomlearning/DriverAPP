package com.example.myapplication.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class DriverResp (
    @SerializedName("data") val userData: JsonObject?,
    @SerializedName("msg") val userMsg: String?
)