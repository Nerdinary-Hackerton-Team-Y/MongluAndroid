package com.project.presentation.login

data class LoginUiState(
    val isLoginSuccess: Boolean
){
    companion object{
        fun init() = LoginUiState(
            isLoginSuccess = false
        )
    }
}