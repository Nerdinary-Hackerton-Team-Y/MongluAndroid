package com.project.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReqRegister(
    @SerializedName("username") val email: String,
    @SerializedName("password") val password: String
)