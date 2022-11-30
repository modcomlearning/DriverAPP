package com.example.myapplication.models


import com.google.gson.annotations.SerializedName
data class TaskPost (
    @SerializedName("task_id") val taskId: String?
)