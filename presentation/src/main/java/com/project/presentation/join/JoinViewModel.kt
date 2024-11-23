package com.project.presentation.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JoinViewModel : ViewModel() {
    private val accountRepository = RepositoryFactory.createAccountRepository()
    private val _uiState = MutableStateFlow(JoinUiState.init())
    val uiState = _uiState.asStateFlow()

    fun updateEtUiState(type: UpdateJoinEtType, data: String) {
        when (type) {
            UpdateJoinEtType.EMAIL -> _uiState.update { prev -> prev.copy(email = data) }
            UpdateJoinEtType.PW -> _uiState.update { prev -> prev.copy(pw = data) }
            UpdateJoinEtType.PW_RE -> _uiState.update { prev -> prev.copy(pw2 = data) }
        }
    }


    fun join(clear: () -> Unit) {
        clear
    }

    fun register(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val isSuccess = accountRepository.register(email = email, password = password)
            _uiState.value = _uiState.value.copy(
                isRegisterSuccess = isSuccess
            )
        }
    }
}

enum class UpdateJoinEtType {
    EMAIL, PW, PW_RE
}