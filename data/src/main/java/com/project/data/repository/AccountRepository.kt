package com.project.data.repository

import android.util.Log
import com.project.data.networtk.NetworkSettings
import com.project.data.networtk.RetrofitClient.makeServerOkHttpClient
import com.project.data.remote.response.ReqRegister
import com.project.data.remote.service.AccountService

class AccountRepository(
    private val accountService: AccountService
) {
    suspend fun login(
        email: String,
        password: String
    ): Boolean {
        return try {
            val res = accountService.login(
                email = email,
                password = password
            )

            if (res.isSuccessful && res.code() == 200) {
                val token = res.body()
                NetworkSettings.getInstance().token = token!!
                makeServerOkHttpClient()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun register(
        email: String,
        password: String
    ): Boolean {
        return try {
            val res = accountService.register(
                ReqRegister(
                    email = email,
                    password = password
                )
            )
            res.isSuccessful && res.code() == 200
        } catch (e: Exception) {
            false
        }

    }

}