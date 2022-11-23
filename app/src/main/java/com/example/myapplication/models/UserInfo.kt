package com.example.myapplication.models

import com.google.gson.annotations.SerializedName
data class UserInfo (
    @SerializedName("email") val userEmail: String?,
    @SerializedName("password") val userPassword: String?
)