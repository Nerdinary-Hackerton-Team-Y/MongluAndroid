package com.project.data.remote.service

import com.project.data.remote.response.ReqRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AccountService {
    @POST("/auth/register")
    suspend fun register(
        @Body req: ReqRegister
    ): Response<String?>

    @POST("/auth/login")
    suspend fun login(
        @Query("username") email: String,
        @Query("password") password: String
    ): Response<String?>

}