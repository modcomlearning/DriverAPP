package com.example.myapplication.models

import com.google.gson.annotations.SerializedName
data class PasswordPost (
    @SerializedName("driver_id") val driverId: String?,
    @SerializedName("current_password") val currentPassword: String?,
    @SerializedName("new_password") val newPassword: String?,
    @SerializedName("confirm_password") val confirmPassword: String?
)