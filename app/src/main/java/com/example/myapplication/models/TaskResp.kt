package com.example.myapplication.models

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class TaskResp (
    @SerializedName("msg") val userMsg: String?
)