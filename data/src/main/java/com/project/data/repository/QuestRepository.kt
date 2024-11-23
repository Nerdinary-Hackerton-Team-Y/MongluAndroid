package com.project.data.repository

import com.project.data.remote.response.ResGetCurrQuest
import com.project.data.remote.service.QuestService

class QuestRepository(
    private val questService: QuestService
) {
    suspend fun getCurrQuest(): ResGetCurrQuest?{
        return try{
            val res = questService.getCurrQuest()
            if(res.isSuccessful && res.code() == 200){
                res.body()
            }else{
                null
            }
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}