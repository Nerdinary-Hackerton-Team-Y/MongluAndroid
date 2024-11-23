package com.project.data.remote.service

import com.project.data.remote.response.ResGetCurrQuest
import retrofit2.Response
import retrofit2.http.GET

interface QuestService {
    @GET("/quest/current")
    suspend fun getCurrQuest(): Response<ResGetCurrQuest>
}