package com.project.data.repository

import com.project.data.networtk.RetrofitClient

object RepositoryFactory {
    fun createPCPRepository(): PCPRepository = PCPRepository(RetrofitClient.shortTermForecastService)
    fun createAccountRepository(): AccountRepository = AccountRepository(RetrofitClient.accountService)
    fun createQuestRepository(): QuestRepository = QuestRepository(RetrofitClient.questService)
}