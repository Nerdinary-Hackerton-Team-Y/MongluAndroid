package com.project.data.remote.service

import com.project.data.remote.response.ServerResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileService {

    @GET("/auth/info")
    suspend fun getInfo()

    @POST("{host}/user/profile")
    suspend fun postUserProfile(
        @Path("host") host: String
    ): ServerResponse<String>

    @DELETE("/user/profile")
    suspend fun deleteUserProfile(): ServerResponse<String>
}