package com.project.data.repository

import com.project.data.networtk.RetrofitClient

object RepositoryFactory {
    fun createPCPRepository(): PCPRepository = PCPRepository(RetrofitClient.shortTermForecastDatasource)


}