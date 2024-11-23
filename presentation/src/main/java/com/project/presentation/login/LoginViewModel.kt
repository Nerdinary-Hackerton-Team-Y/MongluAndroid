package com.project.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState.init())
    val uiState = _uiState.asStateFlow()

    private val accountRepository = RepositoryFactory.createAccountRepository()

    fun login(
        email: String,
        password: String
    ){
        viewModelScope.launch {
            val isLogin = accountRepository.login(
                email = email,
                password = password
            )
            _uiState.value = _uiState.value.copy(
                isLoginSuccess = isLogin
            )
        }
    }
}